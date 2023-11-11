package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.CommonPage;
import life.bienao.springbootinit.entity.Menu;
import life.bienao.springbootinit.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 菜单表
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 新增
     **/
    @PostMapping("/insert")
    public int insert(@RequestBody Menu menu){
        return menuService.insert(menu);
    }

    /**
     * 刪除
     **/
    @GetMapping("/delete")
    public int delete(@RequestParam int id){
        return menuService.delete(id);
    }

    /**
     * 更新
     **/
    @PostMapping("/update")
    public int update(@RequestBody Menu menu){
        return menuService.update(menu);
    }

    /**
     * 查询 根据主键 id 查询
     **/
    @GetMapping("/load")
    public Object load(@RequestParam int id){
        return menuService.load(id);
    }

    /**
     * 查询 分页查询
     **/
    @GetMapping("/pageList")
    public CommonPage<Menu> pageList(@RequestParam(required = false, defaultValue = "1") int offset,
                                     @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return menuService.pageList(offset, pagesize);
    }

}
