package com.whx.service;

import com.whx.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whx.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
public interface IBookService extends IService<Book> {

    void add(Book book);

    RespBean queryByCondition(String bookName, String author,Long current);
}
