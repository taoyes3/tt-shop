package com.taoyes3.shop.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyes3.shop.sys.dao.SysRoleMapper;
import com.taoyes3.shop.sys.dao.SysRoleMenuMapper;
import com.taoyes3.shop.sys.model.SysRole;
import com.taoyes3.shop.sys.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public void saveRoleAndRoleMenu(SysRole role) {
        role.setCreateTime(new Date());
        this.save(role);
        if (CollectionUtil.isEmpty(role.getMenuIdList())) {
            return;
        }

        //保存角色与菜单关系
        sysRoleMenuMapper.insertRoleAndMenu(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleAndRoleMenu(SysRole role) {
        this.updateById(role);
        //删除旧的角色与菜单关系
        sysRoleMenuMapper.deleteMenuByRoleId(role.getRoleId());

        if (CollectionUtil.isEmpty(role.getMenuIdList())) {
            return;
        }
        //保存新的角色与菜单关系
        sysRoleMenuMapper.insertRoleAndMenu(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> roleIds) {
        //1.删除角色
        this.removeByIds(roleIds);
        //2.删除角色和菜单关联
        sysRoleMenuMapper.deleteBatchByRoleIds(roleIds);
        // TODO: 2022/8/3   3.删除角色与用户关联
    }
}
