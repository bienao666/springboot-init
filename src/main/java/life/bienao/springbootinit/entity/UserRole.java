package life.bienao.springbootinit.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description user_role
 */
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

}
