package life.bienao.springbootinit.handler;

import com.alibaba.fastjson.JSON;
import life.bienao.springbootinit.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 * AuthenticationFailureHandler 用来解决身份验证失败的异常(适用表单登录方式)
 */
@Component("customAuthenticationFailureHandler")
@Slf4j
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 认证失败响应JSON字符串，
        Result result = Result.error(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        String message = JSON.toJSONString(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(message);
    }
}
