package life.bienao.springbootinit.entity;

import lombok.Data;

import java.io.Serializable;

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
    private String loginTime;

    /**
     * create_time
     */
    private String createTime;

    /**
     * update_time
     */
    private String updateTime;

}