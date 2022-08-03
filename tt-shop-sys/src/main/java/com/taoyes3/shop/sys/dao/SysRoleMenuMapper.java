package com.taoyes3.shop.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyes3.shop.sys.model.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    //根据角色id 批量添加角色与菜单关系
    void insertRoleAndMenu(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);

    //根据角色id 批量删除角色与菜单关系
    void deleteMenuByRoleId(Long roleId);

    //根据角色id 批量删除角色与菜单关系
    void deleteBatchByRoleIds(@Param("roleIds") List<Long> roleIds);
}
