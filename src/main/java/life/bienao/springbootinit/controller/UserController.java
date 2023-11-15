package life.bienao.springbootinit.controller;

import cn.hutool.core.util.StrUtil;
import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @description 用户
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    /**
     * 注册用户 默认开启白名单
     * @param user
     */
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        User result = userService.loadByUserName(user.getUserName());
        if(null != result){
            throw new RuntimeException("用户已经存在");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.insert(user);
        return "注册成功";
    }

    /**
     * 根据用户查询用户
     * @param username
     */
//    @PreAuthorize("hasAnyAuthority('test')")
    @GetMapping("/loadByUserName")
    public User loadByUserName(@RequestParam String username) {
        User result = userService.loadByUserName(username);
        if(null == result){
            throw new RuntimeException("用户不存在");
        }
        return result;
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.update(user);
        return "注册成功";
    }



}
