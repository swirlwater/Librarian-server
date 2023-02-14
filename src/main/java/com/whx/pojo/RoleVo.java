package com.whx.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("角色权限实体")
public class RoleVo {

    @ApiModelProperty("角色实体")
    private Role role;

    @ApiModelProperty("权限")
    private List<String> permissions;
}
