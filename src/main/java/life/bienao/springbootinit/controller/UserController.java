package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.entity.Result;
import life.bienao.springbootinit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/users")
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
    public Result signup(@RequestBody User user) {
        User result = userService.loadByUserName(user.getUsername());
        if(null != result){
            throw new RuntimeException("用户已经存在");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.insert(user);
        return Result.ok("注册成功");
    }

    /**
     * 根据用户查询用户
     * @param username
     */
    @GetMapping("/loadByUserName")
    public Result loadByUserName(@RequestParam String username) {
        User result = userService.loadByUserName(username);
        if(null == result){
            throw new RuntimeException("用户不存在");
        }
        return Result.ok(result);
    }

}
