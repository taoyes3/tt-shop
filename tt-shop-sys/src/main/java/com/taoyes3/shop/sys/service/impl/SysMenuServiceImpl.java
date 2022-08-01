package com.taoyes3.shop.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyes3.shop.sys.dao.SysMenuMapper;
import com.taoyes3.shop.sys.model.SysMenu;
import com.taoyes3.shop.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> listMenuAndBtn() {
        return sysMenuMapper.listMenuAndBtn();
    }

    @Override
    public List<SysMenu> listRootMenu() {
        return sysMenuMapper.listRootMenu();
    }

    @Override
    public List<SysMenu> listChildrenMenuByParentId(Long parentId) {
        return sysMenuMapper.listChildrenMenuByParentId(parentId);
    }
}
