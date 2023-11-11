package life.bienao.springbootinit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 菜单表
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private Integer visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * create_by
     */
    private Long createBy;

    /**
     * create_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * update_by
     */
    private Long updateBy;

    /**
     * update_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否删除（0未删除 1已删除）
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;

}
