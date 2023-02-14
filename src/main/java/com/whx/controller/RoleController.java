package com.whx.controller;

import com.whx.pojo.RoleVo;
import com.whx.service.IRoleService;
import com.whx.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
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
    @ApiOperation("添加角色")
    public RespBean add(@RequestBody RoleVo roleVo){
        roleService.add(roleVo);
        return RespBean.success();
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除角色")
    public RespBean delete(@RequestParam("ids") Integer[] ids){
        roleService.delete(ids);
        return RespBean.success();
    }

    @PutMapping("/update")
    @ApiOperation("修改角色")
    public RespBean update(@RequestBody RoleVo roleVo){
        roleService.updateRole(roleVo);
        return RespBean.success();
    }

    @GetMapping("/query")
    @ApiOperation("查询角色")
    public RespBean query(String name,Integer currentPage){
        return roleService.queryByName(name,currentPage);
    }
}
