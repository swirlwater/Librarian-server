package com.whx.controller;


import com.whx.pojo.LoginUser;
import com.whx.pojo.User;
import com.whx.service.IUserService;
import com.whx.utils.RedisCache;
import com.whx.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/user")
@Api("UserController")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     * @param user 用户
     * @return 结果
     */
    @PostMapping("/register")
    @ApiOperation("注册")
    public RespBean register(@RequestBody @Validated User user){
        return userService.register(user);
    }

    /**
     * 登录
     * @param user 用户
     * @return 结果
     */
    @PostMapping(value = "/login")
    @ApiOperation("登录")
    public RespBean login(@RequestBody User user){
        return userService.login(user);
    }

    /**
     * 查询用户信息
     * @return 查询结果及用户信息
     */
    @GetMapping("/query")
    @ApiOperation("查询用户信息")
    public RespBean query(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();
        user.setPassword(null);
        return RespBean.success(user);
    }

    /**
     * 更新用户信息
     * @return 结果
     */
    @PutMapping("/update")
    @ApiOperation("更新用户信息")
    public RespBean update(@RequestBody User user){
        //从security中获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //封装用户的新信息
        user.setId(loginUser.getUser().getId());
        user.setUsername(loginUser.getUsername());
        user.setPassword(loginUser.getPassword());
        //更新redis的user信息
        loginUser.setUser(user);
        redisCache.setCacheObject("login:"+user.getId(),loginUser);
        //更新user数据库信息
        userService.updateById(user);
        return RespBean.success();
    }

    /**
     * 修改用户角色
     * @param id 用户id
     * @param roles 角色名数组
     * @return 结果
     */
    @PutMapping("/updateRole")
    @ApiOperation("修改用户角色")
    public RespBean updateRole(@RequestParam("id") Integer id,@RequestParam("roles")String[] roles){
        userService.updateRoleById(id,roles);
        return RespBean.success();
    }

    /**
     * 管理员查询用户
     * @param username 用户名
     * @param currentPage 当前页
     * @return 信息
     */
    @GetMapping("/queryAll")
    @ApiOperation("查询所有用户及其所属角色")
    public RespBean queryUser(String username,Integer currentPage){
        return userService.queryAll(username,currentPage);
    }

    /**
     * 查询用户拥有权限
     * @return 权限详情列表
     */
    @GetMapping("/permissions")
    @ApiOperation("查询用户拥有权限")
    public RespBean queryPermissions(){
        return userService.queryPermissions();
    }

    /**
     * 退出登录
     * @return 结果
     */
    @GetMapping("/logout")
    @ApiOperation("退出登录")
    public RespBean logout(){
        return userService.logout();
    }
}
