package com.whx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whx.pojo.Role;
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

    void add(Role role, String[] permissions);

    void delete(Integer[] ids);

    void updateRole(Role role, String[] permissions);

    RespBean queryByName(String name,Integer currentPage);
}
