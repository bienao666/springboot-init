package life.bienao.springbootinit.service;

import life.bienao.springbootinit.entity.User;

/**
 * @description user
 * @author BEJSON
 * @date 2023-11-04
 */
public interface UserService {

    /**
     * 新增
     */
    int insert(User user);

    /**
     * 删除
     */
    int delete(int id);

    /**
     * 更新
     */
    int update(User user);

    /**
     * 根据主键 id 查询
     */
    User load(int id);

    User loadByUserName(String username);



}
