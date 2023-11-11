package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.CommonPage;
import life.bienao.springbootinit.entity.Menu;
import life.bienao.springbootinit.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description 菜单表
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 新增
     **/
    @RequestMapping("/insert")
    public int insert(Menu menu){
        return menuService.insert(menu);
    }

    /**
     * 刪除
     **/
    @RequestMapping("/delete")
    public int delete(int id){
        return menuService.delete(id);
    }

    /**
     * 更新
     **/
    @RequestMapping("/update")
    public int update(Menu menu){
        return menuService.update(menu);
    }

    /**
     * 查询 根据主键 id 查询
     **/
    @RequestMapping("/load")
    public Object load(int id){
        return menuService.load(id);
    }

    /**
     * 查询 分页查询
     **/
    @RequestMapping("/pageList")
    public CommonPage<Menu> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                               @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return menuService.pageList(offset, pagesize);
    }

}
