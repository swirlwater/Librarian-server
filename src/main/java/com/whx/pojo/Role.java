package com.whx.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色对象")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    @ApiModelProperty("对象id")
    private Integer id;

    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String content;


}
