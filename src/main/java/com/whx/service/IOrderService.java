package com.whx.service;

import com.whx.pojo.Order;
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
public interface IOrderService extends IService<Order> {

    void add(Order order);

    RespBean queryByCondition(String username, String bookName, String author,Long current);

    RespBean agree(Integer id);

    RespBean cancel(Integer id);
}
