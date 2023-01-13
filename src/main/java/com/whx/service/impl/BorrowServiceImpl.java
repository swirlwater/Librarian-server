package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whx.mapper.BookMapper;
import com.whx.mapper.UserMapper;
import com.whx.pojo.Book;
import com.whx.pojo.Borrow;
import com.whx.mapper.BorrowMapper;
import com.whx.pojo.User;
import com.whx.service.IBorrowService;
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
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements IBorrowService {

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void add(Borrow borrow) {
        borrowMapper.insert(borrow);
    }

    @Override
    public RespBean queryByCondition(String username, String bookName, Integer current) {
        //查询用户id
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        Integer userId = userMapper.selectOne(userQueryWrapper).getId();
        //查询书籍id
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("book_name",bookName);
        Integer bookId = bookMapper.selectOne(bookQueryWrapper).getId();
        //查询借阅
        QueryWrapper<Borrow> borrowQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("user_id",userId);
        bookQueryWrapper.eq("book_id",bookId);
        Page<Borrow> page = borrowMapper.selectPage(new Page<>(current, 10), borrowQueryWrapper);
        return RespBean.success(page);
    }
}
