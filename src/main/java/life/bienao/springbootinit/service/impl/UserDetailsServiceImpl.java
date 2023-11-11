package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.entity.LoginUser;
import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.mapper.MenuMapper;
import life.bienao.springbootinit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadByUserName(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //todo 查询对应权限信息 添加到LoginUser中
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user, permissions);
    }

}
