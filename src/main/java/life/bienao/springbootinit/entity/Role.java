package life.bienao.springbootinit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 角色表
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * del_flag
     */
    private Integer delFlag;

    /**
     * create_by
     */
    private Integer createBy;

    /**
     * create_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * update_by
     */
    private Integer updateBy;

    /**
     * update_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

}
