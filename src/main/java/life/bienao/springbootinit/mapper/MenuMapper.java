package life.bienao.springbootinit.mapper;

import life.bienao.springbootinit.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 菜单表
 */
@Mapper
public interface MenuMapper {

    /**
     * 新增
     * @author BEJSON
     * @date 2023/11/11
     **/
    int insert(Menu menu);

    /**
     * 刪除
     * @author BEJSON
     * @date 2023/11/11
     **/
    int delete(int id);

    /**
     * 更新
     * @author BEJSON
     * @date 2023/11/11
     **/
    int update(Menu menu);

    /**
     * 查询 根据主键 id 查询
     * @author BEJSON
     * @date 2023/11/11
     **/
    Menu load(int id);

    /**
     * 查询
     *
     **/
    List<Menu> loadAll();

    /**
     * 根据用户ID查询用户标识
     * @param userid
     * @return
     */
    List<String> selectPermsByUserId(Integer userid);

}
