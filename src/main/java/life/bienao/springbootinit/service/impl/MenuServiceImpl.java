package life.bienao.springbootinit.service.impl;

import com.github.pagehelper.PageHelper;
import life.bienao.springbootinit.entity.CommonPage;
import life.bienao.springbootinit.entity.Menu;
import life.bienao.springbootinit.mapper.MenuMapper;
import life.bienao.springbootinit.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 菜单表
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;


    @Override
    public int insert(Menu menu) {
        return menuMapper.insert(menu);
    }


    @Override
    public int delete(int id) {
        return menuMapper.delete(id);
    }


    @Override
    public int update(Menu menu) {
        return menuMapper.update(menu);
    }


    @Override
    public Menu load(int id) {
        return menuMapper.load(id);
    }


    @Override
    public CommonPage<Menu> pageList(int offset, int pagesize) {
        PageHelper.startPage(offset, pagesize);
        List<Menu> pageList = menuMapper.loadAll();
        return CommonPage.restPage(pageList);
    }

}
