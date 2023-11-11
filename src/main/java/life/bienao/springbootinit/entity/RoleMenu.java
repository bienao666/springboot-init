package life.bienao.springbootinit.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description role_menu
 */
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

}
