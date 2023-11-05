package life.bienao.springbootinit.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import life.bienao.springbootinit.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.net.ConnectException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局处理自定义的业务异常
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        logger.error(e.getMessage(), e);
        return Result.error();
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(Exception e){
        logger.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConnectException.class)
    public Result connectException(ConnectException e){
        logger.error(e.getMessage(), e);
        return Result.error("系统调用异常");
    }

    @ResponseBody
    @ExceptionHandler(ResourceAccessException.class)
    public Result connectException(ResourceAccessException e){
        logger.error(e.getMessage(), e);
        return Result.error("系统之间调用异常");
    }

    @ResponseBody
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public Result expiredJwtException(ExpiredJwtException e) {
        logger.error(e.getMessage(), e);
        return Result.error(401,"Token过期");
    }

    @ExceptionHandler(value = UnsupportedJwtException.class)
    @ResponseBody
    public Result unsupportedJwtException(UnsupportedJwtException e) {
        logger.error(e.getMessage(), e);
        return Result.error(401,"Token签名失败");
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public Result signatureException(SignatureException e) {
        logger.error(e.getMessage(), e);
        return Result.error(401,"Token格式错误");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public Result illegalArgumentException(IllegalArgumentException e) {
        logger.error(e.getMessage(), e);
        return Result.error(401,"Token非法参数异常");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public Result accessDeniedException(AccessDeniedException e) {
        logger.error(e.getMessage(), e);
        return Result.error(401,"Token非法参数异常");
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    @ResponseBody
    public Result malformedJwtException(MalformedJwtException e) {
        logger.error(e.getMessage(), e);
        return Result.error(401,"Token没有被正确构造");
    }
}
