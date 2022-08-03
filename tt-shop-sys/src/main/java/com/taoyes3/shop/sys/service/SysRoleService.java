package com.taoyes3.shop.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyes3.shop.sys.model.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    void saveRoleAndRoleMenu(SysRole role);

    void updateRoleAndRoleMenu(SysRole role);

    void deleteBatch(List<Long> roleIds);
}
