package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whx.pojo.Order;
import com.whx.mapper.OrderMapper;
import com.whx.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 添加订单
     * @param order 订单实体
     */
    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    /**
     * 通过条件查询订单
     * @param username 用户名
     * @param bookName 书名
     * @param current 当前页数
     * @return 查询结果
     */
    @Override
    public RespBean queryByCondition(String username, String bookName, String author,Long current) {
        if (Objects.isNull(bookName)) bookName="";
        if (Objects.isNull(author)) author="";
        if (Objects.isNull(current)) current=1L;
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        queryWrapper.like("book_name",bookName);
        queryWrapper.like("author",author);
        Page<Order> orderPage = orderMapper.selectPage(new Page<>(current, 10), queryWrapper);
        return RespBean.success(orderPage);
    }
}
