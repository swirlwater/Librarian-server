package com.whx.mapper;

import com.whx.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void agree(@Param("id") Integer id);

    void cancel(@Param("id") Integer id);
}
