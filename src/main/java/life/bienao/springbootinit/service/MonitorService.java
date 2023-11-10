package life.bienao.springbootinit.service;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.entity.Thread;

public interface MonitorService {

    /**
     * 获取所有信息
     * @return
     */
    JSONObject getAllInfo();

    /**
     * 服务器信息
     * @return
     */
    Server server();

    /**
     * Java虚拟机信息
     * @return
     */
    Jvm jvm();

    /**
     * CPU
     * @return
     */
    Cpu cpu();

    /**
     * 内存
     * @return
     */
    Memory memory();
    /**
     * 磁盘信息
     * @return
     */
    Disk disk();

    /**
     * 堆/非堆
     * @return
     */

    Heap heap();

    /**
     * 线程信息
     * @return
     */
    Thread thread();

    /**
     * 垃圾回收信息
     * @return
     */
    Gc gc();
}
