package life.bienao.springbootinit.controller;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 授权
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登陆
     * @param user
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody User user){
        return authService.login(user);
    }

    /**
     * 登出
     */
    @GetMapping ("/logout")
    public void logout(){
        authService.logout();
    }
}
