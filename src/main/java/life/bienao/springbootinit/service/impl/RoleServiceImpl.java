package life.bienao.springbootinit.service.impl;

import com.github.pagehelper.PageHelper;
import life.bienao.springbootinit.entity.CommonPage;
import life.bienao.springbootinit.entity.Role;
import life.bienao.springbootinit.mapper.RoleMapper;
import life.bienao.springbootinit.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 角色表
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }


    @Override
    public int delete(int id) {
        return roleMapper.delete(id);
    }


    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }


    @Override
    public Role load(int id) {
        return roleMapper.load(id);
    }


    @Override
    public CommonPage<Role> pageList(int offset, int pagesize) {
        PageHelper.startPage(offset, pagesize);
        List<Role> pageList = roleMapper.loadAll();
        return CommonPage.restPage(pageList);
    }

}
