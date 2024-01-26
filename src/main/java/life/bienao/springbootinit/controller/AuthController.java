package life.bienao.springbootinit.controller;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import life.bienao.springbootinit.constant.ConstantKey;
import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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



    /**
     * 查询登录用户
     */
    @GetMapping("/loadLoginUser")
    public User loadLoginUser(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(ConstantKey.HEADER_KEY);
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token.replace(ConstantKey.BEARER, ""));
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //根据解析到的id查询redis
        LoginUser loginUser = Redis.loginUser.get("login:" + userId);
        return loginUser.getUser();
    }
}
