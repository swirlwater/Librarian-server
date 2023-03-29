package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.mapper.PermissionMapper;
import com.whx.mapper.RoleMapper;
import com.whx.mapper.UserMapper;
import com.whx.mapper.UserRoleMapper;
import com.whx.pojo.*;
import com.whx.service.IUserService;
import com.whx.utils.JwtUtil;
import com.whx.utils.RedisCache;
import com.whx.utils.RespBean;
import com.whx.utils.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 用户注册
     *
     * @param user 用户
     */
    @Override
    public RespBean register(User user) {
        //对密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);
        if (loginUser != null) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        //注册用户
        userMapper.insert(user);
        //赋予角色
        User user1 = userMapper.selectOne(queryWrapper);
        userRoleMapper.setRole(user1.getId());
        return RespBean.success();
    }

    /**
     * 登录
     *
     * @param user 登录用户
     * @return 登录结果及token
     */
    @Override
    public RespBean login(User user) {
        //AuthenticationManager进行用户登录验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证没通过，就给出相应的提示
        if (authenticate == null) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，使用userid生成一个jwt，并将jwt存入redis并返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();//返回查询到的用户信息
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        map.put("user",loginUser.getUser());
        redisCache.setCacheObject("login:" + userid, loginUser);
        return RespBean.success(map);
    }

    /**
     * 注销
     *
     * @return 注销结果
     */
    @Override
    public RespBean logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer userid = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:" + userid);
        return RespBean.success();
    }

    /**
     * 通过用户id更新角色
     *
     * @param id    用户id
     * @param roles 角色名数组
     */
    @Override
    public void updateRoleById(Integer id, String[] roles) {
        //通过用户id删除用户角色关系
        userRoleMapper.deleteByUserId(id);
        //插入用户角色关系表
        for (String role : roles) {
            //根据角色名查询角色id
            Role ro = roleMapper.getRoleByEqualName(role);
            //封装关系并插入表
            UserRole userRole = new UserRole();
            userRole.setUid(id);
            userRole.setRid(ro.getId());
            userRoleMapper.insert(userRole);
        }
    }

    /**
     * 通过用户名查询用户角色实体
     *
     * @param username 用户名
     * @return 结果
     */
    @Override
    public RespBean queryAll(String username, Integer currentPage) {
        if (username == null) username = "";
        List<UserRoleVo> userRoleVos = new ArrayList<>();
        //通过用户名查询用户信息
        List<User> users = userMapper.queryByLikeUsername(username);
        for (User user : users) {
            //通过用户id查询角色名
            List<String> roles = userRoleMapper.queryByUserId(user.getId());
            //封装用户角色实体
            UserRoleVo userRoleVo = new UserRoleVo();
            userRoleVo.setId(user.getId());
            userRoleVo.setUsername(user.getUsername());
            userRoleVo.setRoles(roles);
            userRoleVos.add(userRoleVo);
        }
        return RespBean.success(userRoleVos);
    }

    @Override
    public RespBean queryPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<Permission> permissions=permissionMapper.queryPermissionByUserId(loginUser.getUser().getId());
        return RespBean.success(permissions);
    }

    @Override
    public RespBean updatePassword(String username, String lastPassword, String newPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            return RespBean.error(RespBeanEnum.USER_NOT_ERROR);
        }
        if(user.getPassword().equals(passwordEncoder.encode(lastPassword))){
            return RespBean.error(RespBeanEnum.PASSWORD_ERROR);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userMapper.update(user,queryWrapper);
        return RespBean.success();
    }
}
