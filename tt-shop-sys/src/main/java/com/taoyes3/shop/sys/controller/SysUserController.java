package com.taoyes3.shop.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taoyes3.shop.sys.model.SysUser;
import com.taoyes3.shop.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody SysUser user) {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getUsername, user.getUsername());
        SysUser dbUser = sysUserService.getOne(sysUserLambdaQueryWrapper);
        if (dbUser != null) {
            return ResponseEntity.badRequest().body("该用户已存在！");
        }

        //暂不加密，学完 spring security 再加密
        user.setPassword(user.getPassword());
        //暂不添加角色相关信息
        sysUserService.save(user);

        return ResponseEntity.ok().build();
    }
}
