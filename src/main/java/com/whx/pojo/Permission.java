package com.whx.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限信息")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    @ApiModelProperty("权限id")
    private Integer id;

    @ApiModelProperty("权限路径")
    private String url;

    /**
     * 权限名
     */
    @ApiModelProperty("权限名")
    private String name;

    /**
     * 组件
     */
    @ApiModelProperty("组件")
    private String component;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;
}
