package life.bienao.springbootinit.service;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.User;

public interface AuthService {

    JSONObject login(User user);

    void logout();
}
