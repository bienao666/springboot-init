package life.bienao.springbootinit.service;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.GetEmailCodeEntity;
import life.bienao.springbootinit.entity.RegisterEntity;
import life.bienao.springbootinit.entity.ResetPasswordEntity;
import life.bienao.springbootinit.entity.User;

public interface AuthService {

    JSONObject login(User user);

    void logout();

    void emailCode(GetEmailCodeEntity entity);

    void register(RegisterEntity entity);

    void resetPassword(ResetPasswordEntity entity);
}
