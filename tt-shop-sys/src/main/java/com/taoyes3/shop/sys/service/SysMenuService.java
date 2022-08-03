package com.taoyes3.shop.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyes3.shop.sys.model.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> listMenuAndBtn();

    List<SysMenu> listRootMenu();

    List<SysMenu> listChildrenMenuByParentId(Long parentId);

    List<SysMenu> listSimpleMenuNoButton();

    List<Long> listMenuIdByRoleId(Long roleId);
}
