package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.mapper.PermissionMapper;
import com.whx.mapper.RoleMapper;
import com.whx.mapper.RolePermissionMapper;
import com.whx.pojo.Permission;
import com.whx.pojo.Role;
import com.whx.pojo.RolePermission;
import com.whx.pojo.RolePermissionVo;
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
     */
    @Override
    public void add(Role role, String[] permissions) {
        //添加角色
        roleMapper.insert(role);
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("name",role.getName());
        Role role1 = roleMapper.selectOne(roleQueryWrapper);
        //添加关系
        insertRelated(role1,permissions);
    }

    public void insertRelated(Role role,String[] permissions){
        for (String permission : permissions) {
            //通过名称获取权限实体
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("content", permission);
            Permission permissionEntity = permissionMapper.selectOne(queryWrapper);
            //将角色id和权限id插入关系表
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(role.getId());
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
     */
    @Override
    public void updateRole(Role role, String[] permissions) {
        //更新角色信息
        roleMapper.updateById(role);
        //删除关系
        rolePermissionMapper.deleteByRoleId(role.getId());
        //添加关系
        insertRelated(role,permissions);
    }

    /**
     * 查询角色及权限
     *
     * @param name 角色名
     * @return 角色权限实体
     */
    @Override
    public RespBean queryByName(String name, Integer currentPage) {
        List<RolePermissionVo> rolePermissionVos = new ArrayList<>();
        //通过角色名模糊查询角色
        List<Role> roles = roleMapper.getRoleByLikeName(name);
        for (Role role : roles) {
            //通过角色id查询权限名
            List<String> permissions = rolePermissionMapper.queryByRoleId(role.getId());
            //封装角色权限实体
            RolePermissionVo rolePermissionVo = new RolePermissionVo();
            rolePermissionVo.setId(role.getId());
            rolePermissionVo.setName(role.getName());
            rolePermissionVo.setContent(role.getContent());
            rolePermissionVo.setPermissions(permissions);
            rolePermissionVos.add(rolePermissionVo);
        }
        return RespBean.success(rolePermissionVos);
    }
}
