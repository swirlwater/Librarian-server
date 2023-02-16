package com.whx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("角色权限实体")
public class RolePermissionVo {

    /**
     * 角色主键id
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

    @ApiModelProperty("权限")
    private List<String> permissions;
}
