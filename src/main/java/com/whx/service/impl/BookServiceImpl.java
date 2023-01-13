package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whx.pojo.Book;
import com.whx.mapper.BookMapper;
import com.whx.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 添加图书
     * @param book 图书信息
     */
    @Override
    public void add(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public RespBean queryByCondition(String bookName, String author,Long current) {
        Page<Book> page = new Page<>(current,10);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name",bookName);
        queryWrapper.like("author",author);
        Page<Book> bookPage = bookMapper.selectPage(page,queryWrapper);
        System.out.println(bookPage.getRecords());
        return RespBean.success(bookPage);
    }
}
