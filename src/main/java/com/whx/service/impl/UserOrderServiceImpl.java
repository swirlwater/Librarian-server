package com.whx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.mapper.UserOrderMapper;
import com.whx.pojo.UserOrder;
import com.whx.service.IUserOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wu
 * @since 2023-01-12
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {

}
