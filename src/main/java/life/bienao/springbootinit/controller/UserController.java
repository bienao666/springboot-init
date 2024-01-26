package life.bienao.springbootinit.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import life.bienao.springbootinit.constant.ConstantKey;
import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.LoginUser;
import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.service.UserService;
import life.bienao.springbootinit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * 注册用户 默认开启白名单
     * @param user
     */
    @PostMapping("/signup")
    public String signup(@Validated @RequestBody User user) {
        User result = userService.loadByUserName(user.getUserName());
        if(null != result){
            throw new RuntimeException("用户已经存在");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.insert(user);
        return "注册成功";
    }

    /**
     * 修改信息
     * @param user
     */
    @PostMapping("/update")
    public String update(@RequestBody User user) {
        User result = userService.load(user.getId());
        if(null == result){
            throw new RuntimeException("用户不存在");
        }
        if(StrUtil.isEmpty(user.getPassword())){
            throw new RuntimeException("密码不能为空");
        }
        if(user.getPassword().length() < 6){
            throw new RuntimeException("密码长度不能小于6位");
        }
        //此接口不修改密码
        user.setPassword(null);
        userService.update(user);
        return "注册成功";
    }

    /**
     * 修改密码
     * @param param
     */
    @PostMapping("/updateUserPwd")
    public String updateUserPwd(@RequestBody JSONObject param) {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();
        String oldPassword = param.getString("oldPassword");
        String newPassword = param.getString("newPassword");
        if(StrUtil.isEmpty(oldPassword)){
            throw new RuntimeException("旧密码不能为空");
        }
        if(StrUtil.isEmpty(newPassword)){
            throw new RuntimeException("新密码不能为空");
        }
        if(newPassword.length() < 6){
            throw new RuntimeException("密码长度不能小于6位");
        }

        if (bCryptPasswordEncoder.encode(oldPassword).equals(user.getPassword())){
            throw new RuntimeException("旧密码错误");
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userService.update(user);
        //退出登陆
        authService.logout();
        return "修改成功";
    }



}
