package life.bienao.springbootinit.mapper;

import life.bienao.springbootinit.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description user
 * @author BEJSON
 * @date 2023-11-04
 */
@Mapper
public interface UserMapper {

    /**
     * 新增
     * @author BEJSON
     * @date 2023/11/04
     **/
    int insert(User user);

    /**
     * 刪除
     * @author BEJSON
     * @date 2023/11/04
     **/
    int delete(int id);

    /**
     * 更新
     * @author BEJSON
     * @date 2023/11/04
     **/
    int update(User user);

    /**
     * 查询 根据主键 id 查询
     * @author BEJSON
     * @date 2023/11/04
     **/
    User load(int id);

    /**
     * 查询 根据用户名 查询
     * @param username
     * @return
     */
    User loadByUserName(String username);
}
