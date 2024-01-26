package life.bienao.springbootinit.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.service.MailService;
import life.bienao.springbootinit.service.UserService;
import life.bienao.springbootinit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public JSONObject login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败！");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        JSONObject result = new JSONObject();
        result.put("token", "Bearer " + jwt);
        Redis.loginUser.put("login:" + userId, loginUser);
        return result;

    }

    @Override
    public void logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //删除用户数据
        Redis.loginUser.remove("login:" + userId);
    }

    @Override
    public void emailCode(GetEmailCodeEntity entity) {
        String email = entity.getEmail();
        String username = email;
        String emailCode = RandomUtil.randomNumbers(6);
        EmailCodeEntity emailCodeEntity = new EmailCodeEntity(emailCode, username,email,1);
        Redis.timedCache.put(RedisTransKey.setEmailKey(username), JSONObject.toJSONString(emailCodeEntity), 60 * 1000);
        mailService.sendCodeMailMessage(email, emailCodeEntity.getEmailCode());
    }

    @Override
    public void register(RegisterEntity entity) {
        String username = entity.getUsername();
        username = username.replaceAll(" ","");
        String authCode = entity.getAuthCode();
//        先检验一下验证码，对不对，邮箱有没有被更改
        if(Redis.timedCache.get(RedisTransKey.getEmailKey(username)) != null){
            String redisTransKey = Redis.timedCache.get(RedisTransKey.getEmailKey(username));
            EmailCodeEntity emailCodeEntity = JSON.parseObject(redisTransKey, EmailCodeEntity.class);
            if(username.equals(emailCodeEntity.getUsername())){
                if(authCode.equals(emailCodeEntity.getEmailCode())){
                    //开始封装用户并进行存储
                    User user = new User();
                    user.setUserName(username);
                    user.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
                    user.setEmail(entity.getEmail());
                    user.setNickName(entity.getNickName());
//                    用户状态，1-正常 2-警告 3-封禁
                    user.setStatus(1);
                    userService.insert(user);
                    Redis.timedCache.remove(RedisTransKey.getEmailKey(username));
                }else {
                    throw new RuntimeException("邮箱验证码错误");
                }
            }else {
                throw new RuntimeException("疑似恶意操作");
            }
        }else {
            throw new RuntimeException("操作超时");
        }
    }

    @Override
    public void resetPassword(ResetPasswordEntity entity) {
        String email = entity.getEmail();
        String username = email;
        String authCode = entity.getAuthCode();
//        先检验一下验证码，对不对，邮箱有没有被更改
        if(Redis.timedCache.get(RedisTransKey.getEmailKey(username)) != null){
            String redisTransKey = Redis.timedCache.get(RedisTransKey.getEmailKey(username));
            EmailCodeEntity emailCodeEntity = JSON.parseObject(redisTransKey, EmailCodeEntity.class);
            if(username.equals(emailCodeEntity.getUsername())){
                if(authCode.equals(emailCodeEntity.getEmailCode())){
                    //开始修改密码
                    User user = userService.loadByUserName(username);
                    User update = new User();
                    update.setId(user.getId());
                    update.setPassword(bCryptPasswordEncoder.encode(entity.getNewPassword()));
                    userService.update(update);
                    Redis.timedCache.remove(RedisTransKey.getEmailKey(username));
                }else {
                    throw new RuntimeException("邮箱验证码错误");
                }
            }else {
                throw new RuntimeException("疑似恶意操作");
            }
        }else {
            throw new RuntimeException("操作超时");
        }
    }
}
