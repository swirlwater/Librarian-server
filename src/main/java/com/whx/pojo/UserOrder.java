package com.whx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wu
 * @since 2023-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId
    private Integer userId;

    /**
     * 订单id
     */
    private Integer orderId;


}
