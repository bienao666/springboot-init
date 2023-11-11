package life.bienao.springbootinit.service;

import life.bienao.springbootinit.entity.CommonPage;
import life.bienao.springbootinit.entity.Menu;


/**
 * @description 菜单表
 */
public interface MenuService {

    /**
     * 新增
     */
    public int insert(Menu menu);

    /**
     * 删除
     */
    public int delete(int id);

    /**
     * 更新
     */
    public int update(Menu menu);

    /**
     * 根据主键 id 查询
     */
    public Menu load(int id);

    /**
     * 分页查询
     */
    public CommonPage<Menu> pageList(int offset, int pagesize);

}
