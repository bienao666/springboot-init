package life.bienao.springbootinit.mapper;

import life.bienao.springbootinit.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description user
 */
@Mapper
public interface UserMapper {

    /**
     * 新增
     **/
    int insert(User user);

    /**
     * 刪除
     **/
    int delete(int id);

    /**
     * 更新
     **/
    int update(User user);

    /**
     * 查询
     **/
    User load(int id);

    /**
     * 查询
     **/
    List<User> loadAll();

    /**
     * 查询 根据用户名 查询
     * @param userName
     * @return
     */
    User loadByUserName(String userName);
}
