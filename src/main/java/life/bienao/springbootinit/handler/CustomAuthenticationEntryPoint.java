package life.bienao.springbootinit.handler;

import cn.hutool.json.JSONUtil;
import life.bienao.springbootinit.entity.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义认证拦截器
 * @desc AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = Result.error(1000, "权限认证失败");
        String message = JSONUtil.toJsonStr(result);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
    }

}