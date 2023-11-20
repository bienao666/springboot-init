package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.service.RestartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 服务
 */
@RestController
@RequestMapping(value = "/server")
public class RestartController {

    @Autowired
    private RestartService restartService;

    @GetMapping("/restart")
    public String restart() {
        restartService.restart();
        return "正在重启...";
    }
}
