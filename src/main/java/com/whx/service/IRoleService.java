package com.whx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whx.pojo.Role;
import com.whx.pojo.RoleVo;
import com.whx.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
public interface IRoleService extends IService<Role> {

    void add(RoleVo roleVo);

    void delete(Integer[] ids);

    void updateRole(RoleVo roleVo);

    RespBean queryByName(String name,Integer currentPage);
}
