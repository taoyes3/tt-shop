package com.taoyes3.shop.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyes3.shop.sys.model.SysMenu;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> listMenuAndBtn();

    List<SysMenu> listRootMenu();

    List<SysMenu> listChildrenMenuByParentId(Long parentId);

    List<SysMenu> listSimpleMenuNoButton();

    List<Long> listMenuIdByRoleId(Long roleId);
}
