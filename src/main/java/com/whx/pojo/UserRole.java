package com.whx.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色关系类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户角色对象")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer uid;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private Integer rid;


}
