package com.whx.controller;

import com.whx.pojo.Role;
import com.whx.service.IRoleService;
import com.whx.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/add")
    @PreAuthorize("@ex.hasAuthority('sys:user:roleManage')")
    @ApiOperation("添加角色")
    public RespBean add(Role role, @RequestParam("permissions") String[] permissions) {
        roleService.add(role, permissions);
        return RespBean.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ex.hasAuthority('sys:user:roleManage')")
    @ApiOperation("删除角色")
    public RespBean delete(@RequestParam("ids") Integer[] ids) {
        roleService.delete(ids);
        return RespBean.success();
    }

    @PutMapping("/update")
    @PreAuthorize("@ex.hasAuthority('sys:user:userManage')")
    @ApiOperation("修改角色")
    public RespBean update(Role role, @RequestParam("permissions") String[] permissions) {
        roleService.updateRole(role, permissions);
        return RespBean.success();
    }

    @GetMapping("/query")
    @PreAuthorize("@ex.hasAuthority('sys:user:userManage')")
    @ApiOperation("查询角色")
    public RespBean query(String name, Integer currentPage) {
        return roleService.queryByName(name, currentPage);
    }

    @GetMapping("/queryAll")
    @PreAuthorize("@ex.hasAuthority('sys:user:userManage')")
    @ApiOperation("查询所有角色")
    public RespBean queryAll() {
        List<Role> roles = roleService.list();
        List<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getContent());
        }
        return RespBean.success(roleNames);
    }
}
