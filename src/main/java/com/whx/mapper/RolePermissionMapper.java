package com.whx.mapper;

import com.whx.pojo.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void deleteByRoleId(@Param("id") Integer id);

    List<String> queryByRoleId(@Param("id") Integer id);

}
