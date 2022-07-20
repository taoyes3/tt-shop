package com.taoyes3.shop.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyes3.shop.sys.dao.SysUserMapper;
import com.taoyes3.shop.sys.model.SysUser;
import com.taoyes3.shop.sys.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
