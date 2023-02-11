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
        return RespBean.success(user);
    }

    /**
     * 更新用户信息
     * @return 结果
     */
    @PutMapping("/update")
    @ApiOperation("更新用户信息")
    public RespBean update(User user){
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
     * 退出登录
     * @return 结果
     */
    @GetMapping("/logout")
    @ApiOperation("退出登录")
    public RespBean logout(){
        return userService.logout();
    }
}
