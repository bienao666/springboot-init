package life.bienao.springbootinit.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * username
     */
    private String username;

    /**
     * email
     */
    private String email;

    /**
     * password
     */
    private String password;

    /**
     * login_time
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String loginTime;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}