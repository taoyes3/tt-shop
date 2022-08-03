package com.taoyes3.shop.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyes3.shop.sys.model.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    void saveRoleAndRoleMenu();
}
