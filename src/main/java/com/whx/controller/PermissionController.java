package com.whx.controller;


import com.whx.pojo.Permission;
import com.whx.service.IPermissionService;
import com.whx.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/queryAll")
    @ApiOperation("查询所有权限名")
    public RespBean queryAll(){
        List<Permission> permissions = permissionService.list();
        List<String> permissionNames=new ArrayList<>();
        for (Permission permission :
                permissions) {
            permissionNames.add(permission.getName());
        }
        return RespBean.success(permissionNames);
    }
}
