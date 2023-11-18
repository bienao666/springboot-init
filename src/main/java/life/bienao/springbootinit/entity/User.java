package life.bienao.springbootinit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @description 用户表
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 账号状态（0正常 1停用）
     */
    @Range(min = 0, max = 1, message = "账号状态只能是0或1")
    private Integer status;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String phonenumber;

    /**
     * 用户性别（0男，1女，2未知）
     */
    @Range(min = 0, max = 1, message = "用户性别只能是0或1或2")
    private Integer sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型（0管理员，1普通用户）
     */
    @Range(min = 0, max = 1, message = "用户类型只能是0或1")
    private Integer userType;

    /**
     * 创建人的用户id
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;

}