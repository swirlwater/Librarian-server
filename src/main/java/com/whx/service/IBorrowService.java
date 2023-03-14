package com.whx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whx.pojo.Borrow;
import com.whx.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
public interface IBorrowService extends IService<Borrow> {

    RespBean requestBorrow(Integer id);

    RespBean queryByCondition(String username, String bookName,String author, Integer current);

    RespBean add(Borrow borrow);

    RespBean agreeLend(Integer id);

    RespBean requestRepaid(Integer id);

    RespBean agreeRepaid(Integer id);
}
