package com.whx.service;

import com.whx.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whx.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
public interface IUserService extends IService<User> {

    /**
     * 用户注册
     * @param user 用户信息
     */
    RespBean register(User user);

    /**
     * 用户登录
     * @param user 登录用户
     * @return 登录结果
     */
    RespBean login(User user);

    /**
     * 退出
     * @return 结果
     */
    RespBean logout();
}
