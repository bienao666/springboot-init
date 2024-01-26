package life.bienao.springbootinit.controller;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.GetEmailCodeEntity;
import life.bienao.springbootinit.entity.RegisterEntity;
import life.bienao.springbootinit.entity.ResetPasswordEntity;
import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public void register(@RequestBody @Validated RegisterEntity entity){
        authService.register(entity);
    }

    /**
     * 验证码
     * @return
     */
    @PostMapping("/emailCode")
    public void emailCode(@RequestBody @Validated GetEmailCodeEntity entity){
        authService.emailCode(entity);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/resetPassword")
    public void resetPassword(@RequestBody @Validated ResetPasswordEntity entity){
        authService.resetPassword(entity);
    }
}
