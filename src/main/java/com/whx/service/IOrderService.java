package com.whx.service;

import com.whx.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
public interface IOrderService extends IService<Order> {

    void add(Order order);
}
