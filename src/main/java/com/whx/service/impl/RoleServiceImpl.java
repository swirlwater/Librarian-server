package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.mapper.PermissionMapper;
import com.whx.mapper.RoleMapper;
import com.whx.mapper.RolePermissionMapper;
import com.whx.pojo.Permission;
import com.whx.pojo.Role;
import com.whx.pojo.RolePermission;
import com.whx.pojo.RoleVo;
import com.whx.service.IRoleService;
import com.whx.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 添加角色
     *
     * @param roleVo 角色权限实体
     */
    @Override
    public void add(RoleVo roleVo) {
        //添加角色
        int rid = roleMapper.insert(roleVo.getRole());
        //添加关系
        for (String permission : roleVo.getPermissions()) {
            //通过名称获取权限实体
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", permission);
            Permission permissionEntity = permissionMapper.selectOne(queryWrapper);
            //将角色id和权限id插入关系表
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(rid);
            rolePermission.setPid(permissionEntity.getId());
            rolePermissionMapper.insert(rolePermission);
        }
    }

    /**
     * 删除角色
     *
     * @param ids 角色id集合
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            //删除关系表关系
            rolePermissionMapper.deleteByRoleId(id);
            //删除角色
            roleMapper.deleteById(id);
        }
    }

    /**
     * 修改角色
     *
     * @param roleVo 角色关系实体
     */
    @Override
    public void updateRole(RoleVo roleVo) {
        //更新角色信息
        roleMapper.updateById(roleVo.getRole());
        //删除关系
        rolePermissionMapper.deleteByRoleId(roleVo.getRole().getId());
        //添加关系
        for (String permission : roleVo.getPermissions()) {
            //通过名称获取权限实体
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", permission);
            Permission permissionEntity = permissionMapper.selectOne(queryWrapper);
            //将角色id和权限id插入关系表
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(roleVo.getRole().getId());
            rolePermission.setPid(permissionEntity.getId());
            rolePermissionMapper.insert(rolePermission);
        }
    }

    /**
     * 查询角色及权限
     *
     * @param name 角色名
     * @return 角色权限实体
     */
    @Override
    public RespBean queryByName(String name, Integer currentPage) {
        List<RoleVo> roleVos = new ArrayList<>();
        //通过角色名模糊查询角色
        List<Role> roles = roleMapper.getRoleByLikeName(name);
        for (Role role : roles) {
            //通过角色id查询权限
            List<String> permissions = rolePermissionMapper.queryByRoleId(role.getId());
            //封装角色权限实体
            RoleVo roleVo = new RoleVo();
            roleVo.setRole(role);
            roleVo.setPermissions(permissions);
            roleVos.add(roleVo);
        }
        return RespBean.success(roleVos);
    }
}
