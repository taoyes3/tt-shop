package com.taoyes3.shop.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyes3.shop.common.util.PageParam;
import com.taoyes3.shop.sys.model.SysRole;
import com.taoyes3.shop.sys.service.SysMenuService;
import com.taoyes3.shop.sys.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/page")
    public ResponseEntity<IPage<SysRole>> page(String roleName, PageParam<SysRole> page) {
        //查询构造器
        LambdaQueryWrapper<SysRole> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.like(StrUtil.isNotBlank(roleName), SysRole::getRoleName, roleName);

        IPage<SysRole> roleIPage = sysRoleService.page(page, roleLambdaQueryWrapper);
        return ResponseEntity.ok(roleIPage);
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SysRole role) {
        sysRoleService.saveRoleAndRoleMenu(role);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info/{roleId}")
    public ResponseEntity<SysRole> info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> menuList = sysMenuService.listMenuIdByRoleId(roleId);
        role.setMenuIdList(menuList);

        return ResponseEntity.ok(role);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody SysRole role) {
        sysRoleService.updateRoleAndRoleMenu(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody List<Long> roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return ResponseEntity.ok().build();
    }
}
