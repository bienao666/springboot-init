package life.bienao.springbootinit.mapper;

import life.bienao.springbootinit.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description role_menu
 */
@Mapper
public interface RoleMenuMapper {

    /**
     * 新增
     **/
    int insert(RoleMenu roleMenu);

    /**
     * 刪除
     **/
    int delete(int id);

    /**
     * 更新
     **/
    int update(RoleMenu roleMenu);

    /**
     * 查询 根据主键 id 查询
     **/
    RoleMenu load(int id);

    /**
     * 查询 分页查询
     **/
    List<RoleMenu> loadByRoleId();

}
