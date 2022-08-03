package com.taoyes3.shop.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.taoyes3.shop.common.exception.ShopBindException;
import com.taoyes3.shop.sys.constant.Constant;
import com.taoyes3.shop.sys.constant.MenuType;
import com.taoyes3.shop.sys.model.SysMenu;
import com.taoyes3.shop.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取菜单页面的表
     *
     * @return
     */
    @GetMapping("/table")
    public ResponseEntity<List<SysMenu>> table() {
        List<SysMenu> sysMenus = sysMenuService.listMenuAndBtn();
        return ResponseEntity.ok(sysMenus);
    }

    /**
     * 所有菜单列表(用于新建、修改角色时 获取菜单的信息)
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<SysMenu>> list() {
        List<SysMenu> sysMenus = sysMenuService.listSimpleMenuNoButton();
        return ResponseEntity.ok(sysMenus);
    }

    /**
     * 选择菜单
     *
     * @return
     */
    @GetMapping("/listRootMenu")
    public ResponseEntity<List<SysMenu>> listRootMenu() {
        //查询列表数据
        List<SysMenu> sysMenus = sysMenuService.listRootMenu();
        return ResponseEntity.ok(sysMenus);
    }

    /**
     * 选择子菜单
     *
     * @param parentId
     * @return
     */
    @GetMapping("/listChildrenMenu")
    public ResponseEntity<List<SysMenu>> listChildren(Long parentId) {
        //查询列表数据
        List<SysMenu> sysMenus = sysMenuService.listChildrenMenuByParentId(parentId);
        return ResponseEntity.ok(sysMenus);
    }

    /**
     * 菜单信息
     *
     * @param menuId
     * @return
     */
    @GetMapping("/info/{menuId}")
    public ResponseEntity<SysMenu> info(@PathVariable("menuId") Long menuId) {
        SysMenu menu = sysMenuService.getById(menuId);
        return ResponseEntity.ok(menu);
    }

    /**
     * 保存
     *
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.save(menu);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.updateById(menu);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> delete(@PathVariable Long menuId) {
        if (menuId <= Constant.SYS_MENU_MAX_ID) {
            return ResponseEntity.badRequest().body("系统菜单，不能删除");
        }
        List<SysMenu> childrenMenus = sysMenuService.listChildrenMenuByParentId(menuId);
        if (childrenMenus.size() > 0) {
            return ResponseEntity.badRequest().body("请先删除子菜单或按钮");
        }
        // TODO: 2022/8/1 菜单和角色关系暂不删除...
        return ResponseEntity.ok().build();
    }

    private void verifyForm(SysMenu menu) {
        //菜单 url
        if (menu.getType() == MenuType.MENU.getValue()) {
            if (StrUtil.isBlank(menu.getUrl())) {
                throw new ShopBindException("菜单URL不能为空");
            }
        }
        if (Objects.equals(menu.getMenuId(), menu.getParentId())) {
            throw new ShopBindException("自己不能是自己的上级");
        }
        //上级菜单类型
        int parentType = MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        //目录、菜单
        if (menu.getType() == MenuType.CATALOG.getValue() || menu.getType() == MenuType.MENU.getValue()) {
            if (parentType != MenuType.CATALOG.getValue()) {
                throw new ShopBindException("上级菜单只能为目录类型");
            }
            return;
        }
        //按钮
        if (menu.getType() == MenuType.BUTTON.getValue()) {
            if (parentType != MenuType.MENU.getValue()) {
                throw new ShopBindException("上级菜单只能为菜单类型");
            }
        }
    }
}
