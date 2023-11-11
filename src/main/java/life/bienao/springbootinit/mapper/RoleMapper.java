package life.bienao.springbootinit.mapper;

import life.bienao.springbootinit.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 角色表
 */
@Mapper
public interface RoleMapper {

    /**
     * 新增
     **/
    int insert(Role role);

    /**
     * 刪除
     **/
    int delete(int id);

    /**
     * 更新
     **/
    int update(Role role);

    /**
     * 查询 根据主键 id 查询
     **/
    Role load(int id);

    /**
     * 查询 分页查询
     **/
    List<Role> loadAll();

}
