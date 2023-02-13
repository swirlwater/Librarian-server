package com.whx.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限关系类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色权限对象")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private Integer rid;

    /**
     * 权限id
     */
    @ApiModelProperty("权限id")
    private Integer pid;


}
