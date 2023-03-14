package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.whx.mapper.PermissionMapper;
import com.whx.mapper.UserMapper;
import com.whx.pojo.LoginUser;
import com.whx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息业务实现类
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询登录用户信息
     * @param username 用户名
     * @return 登录用户实体
     * @throws UsernameNotFoundException 用户名未发现
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        //查询用户
        User user = userMapper.selectOne(queryWrapper);
        if (user==null){
            //如果没有查询到用户就抛出异常
            throw new RuntimeException("用户名或密码错误");
        }
        //封装用户权限
        List<String> permissions=permissionMapper.queryPermission(user.getId());
        return new LoginUser(user,permissions);
    }
}
