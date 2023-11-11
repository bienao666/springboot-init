package life.bienao.springbootinit.service;

import life.bienao.springbootinit.entity.CommonPage;
import life.bienao.springbootinit.entity.Role;

/**
 * @description 角色表
 */
public interface RoleService {

    /**
     * 新增
     */
    public int insert(Role role);

    /**
     * 删除
     */
    public int delete(int id);

    /**
     * 更新
     */
    public int update(Role role);

    /**
     * 根据主键 id 查询
     */
    public Role load(int id);

    /**
     * 分页查询
     */
    public CommonPage<Role> pageList(int offset, int pagesize);

}
