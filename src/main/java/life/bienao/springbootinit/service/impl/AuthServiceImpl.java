package life.bienao.springbootinit.service.impl;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.LoginUser;
import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

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
}
