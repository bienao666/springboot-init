package life.bienao.springbootinit.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.entity.Thread;
import life.bienao.springbootinit.service.MonitorService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.*;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MonitorServiceImpl implements MonitorService {
    @Override
    public JSONObject getAllInfo() {
        JSONObject result = new JSONObject();
        result.put("Server Info", server());
        result.put("JVM", jvm());
        result.put("CPU", cpu());
        result.put("Memory", memory());
        result.put("Disk", disk());
        result.put("Heap/NonHeap", heap());
        result.put("Thread", thread());
        result.put("GC", gc());
        return result;
    }

    /**
     * 服务器信息
     *
     * @return {@link Server}
     */
    @SneakyThrows
    @Override
    public Server server() {
        InetAddress inetAddress = InetAddress.getLocalHost();
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        return Server.builder()
                .hostName(inetAddress.getHostName())
                .osName(operatingSystemMXBean.getName())
                .osVersion(operatingSystemMXBean.getVersion())
                .arch(operatingSystemMXBean.getArch())
                .localIp(inetAddress.getHostAddress())
                .build();
    }

    /**
     * Java虚拟机信息
     *
     * @return
     */
    @Override
    public Jvm jvm() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Map<String, String> systemProperties = runtimeMXBean.getSystemProperties();
        return Jvm.builder()
                .vmName(runtimeMXBean.getVmName())
                .jdkVersion(systemProperties.get("java.runtime.version"))
                .javaHome(systemProperties.get("java.home"))
                .pid(systemProperties.get("PID"))
                .startTime(LocalDateTimeUtil.of(runtimeMXBean.getStartTime()))
                .runtime(runtimeMXBean.getUptime())
                .startParameters(runtimeMXBean.getInputArguments())
                .build();
    }

    /**
     * CPU
     *
     * @return
     */
    @Override
    public Cpu cpu() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        JSONObject operatingSystemJson = JSON.parseObject(JSON.toJSONString(operatingSystemMXBean));
        return Cpu.builder()
                .availableProcssors(operatingSystemMXBean.getAvailableProcessors())
                .systemCpuUsage(operatingSystemJson.getBigDecimal("systemCpuLoad"))
                .processCpuUsage(operatingSystemJson.getBigDecimal("processCpuLoad"))
                .build();
    }

    /**
     * 内存
     *
     * @return
     */
    @Override
    public Memory memory() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        JSONObject operatingSystemJson = JSON.parseObject(JSON.toJSONString(operatingSystemMXBean));
        long totalPhysicalMemory = operatingSystemJson.getLongValue("totalPhysicalMemorySize");
        long freePhysicalMemory = operatingSystemJson.getLongValue("freePhysicalMemorySize");
        long usedPhysicalMemory = totalPhysicalMemory - freePhysicalMemory;
        Runtime runtime = Runtime.getRuntime();
        return Memory.builder()
                .totalPhysicalMemory(totalPhysicalMemory)
                .freePhysicalMemory(freePhysicalMemory)
                .usedPhysicalMemory(usedPhysicalMemory)
                .physicalMemoryUsage(NumberUtil.toBigDecimal(NumberUtil.div(usedPhysicalMemory, totalPhysicalMemory)))
                .totalMemory(runtime.totalMemory())
                .freeMemory(runtime.freeMemory())
                .usedMemory(runtime.totalMemory() - runtime.freeMemory()).maxMemory(runtime.maxMemory())
                .maxUseMemory(runtime.maxMemory() - runtime.totalMemory() + runtime.freeMemory())
                .memoryUsage(NumberUtil.toBigDecimal(NumberUtil.div((double) runtime.totalMemory() - runtime.freeMemory(), runtime.totalMemory())))
                .build();
    }

    /**
     * 磁盘信息
     *
     * @return
     */
    @Override
    public Disk disk() {
        List<Disk.Drive> drives = Stream.of(File.listRoots()).filter(file -> file.getTotalSpace() != 0).map(file -> Disk.Drive.builder()
                .name(file.toString())
                .totalSpace(file.getTotalSpace())
                .freeSpace(file.getFreeSpace())
                .usedSpace(file.getTotalSpace() - file.getFreeSpace())
                .driveUsage(NumberUtil.toBigDecimal(NumberUtil.div((double) file.getTotalSpace() - file.getFreeSpace(), file.getTotalSpace())))
                .build()
        ).collect(Collectors.toList());
        return new Disk(drives);
    }

    /**
     * 堆/非堆
     *
     * @return
     */
    @Override
    public Heap heap() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        return Heap.builder()
                .heapInit(heapMemoryUsage.getInit())
                .heapMaxMemory(heapMemoryUsage.getMax())
                .heapUsedMemory(heapMemoryUsage.getUsed())
                .heapFreeMemory(heapMemoryUsage.getMax() - heapMemoryUsage.getUsed())
                .nonHeapInit(nonHeapMemoryUsage.getInit())
                .nonHeapMaxMemory(nonHeapMemoryUsage.getMax())
                .nonHeapUsedMemory(nonHeapMemoryUsage.getUsed())
                .nonHeapFreeMemory(nonHeapMemoryUsage.getMax() - nonHeapMemoryUsage.getUsed())
                .build();
    }

    /**
     * 线程信息
     *
     * @return
     */
    @Override
    public Thread thread() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        int threadCount = threadMXBean.getThreadCount();
        int peakThreadCount = threadMXBean.getPeakThreadCount();
        int daemonThreadCount = threadMXBean.getDaemonThreadCount();
        int nonDaemonThreadCount = threadCount - daemonThreadCount;
        long totalStartedThreadCount = threadMXBean.getTotalStartedThreadCount();

        return new Thread(threadCount, peakThreadCount, daemonThreadCount, nonDaemonThreadCount, totalStartedThreadCount);
    }


    /**
     * 垃圾回收信息
     *
     * @return
     */
    @Override
    public Gc gc() {
        long collectionCount = 0, collectionTime = 0;
        for (GarbageCollectorMXBean garbageCollectorMXBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            collectionCount += garbageCollectorMXBean.getCollectionCount();
            collectionTime += garbageCollectorMXBean.getCollectionTime();
        }
        return Gc.builder().collectionCount(collectionCount).collectionTime(collectionTime).build();
    }
}
