package life.bienao.springbootinit.mapper;

import life.bienao.springbootinit.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description user_role
 */
@Mapper
public interface UserRoleMapper {

    /**
     * 新增
     **/
    int insert(UserRole userRole);

    /**
     * 刪除
     **/
    int delete(int id);

    /**
     * 更新
     **/
    int update(UserRole userRole);

    /**
     * 查询 根据主键 id 查询
     **/
    UserRole load(int id);

    /**
     * 查询 分页查询
     **/
    List<UserRole> loadByUserId();

}
