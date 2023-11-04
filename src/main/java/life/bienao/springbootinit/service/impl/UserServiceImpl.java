package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.entity.User;
import life.bienao.springbootinit.mapper.UserMapper;
import life.bienao.springbootinit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }


    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }


    @Override
    public int update(User user) {
        return userMapper.update(user);
    }


    @Override
    public User load(int id) {
        return userMapper.load(id);
    }

    @Override
    public User loadByUserName(String username) {
        return userMapper.loadByUserName(username);
    }

}
