package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.mapper.BookMapper;
import com.whx.mapper.BorrowMapper;
import com.whx.pojo.Book;
import com.whx.pojo.Borrow;
import com.whx.pojo.LoginUser;
import com.whx.service.IBorrowService;
import com.whx.utils.RespBean;
import com.whx.utils.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
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
    private BookMapper bookMapper;

    /**
     * 用户添加借阅
     *
     * @param id 书籍id
     */
    @Override
    public RespBean requestBorrow(Integer id) {
        //查询书籍
        Book book = bookMapper.selectById(id);
        //检查
        if (book == null) return RespBean.error(RespBeanEnum.BOOK_ERROR);
        if (book.getNum() <= 0) return RespBean.error(RespBeanEnum.NUM_ERROR);
        //减少库存
        book.setNum(book.getNum() - 1);
        bookMapper.updateById(book);
        //封装借阅
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getUsername();
        Borrow borrow = new Borrow();
        borrow.setBookName(book.getBookName());
        borrow.setAuthor(book.getAuthor());
        borrow.setUsername(username);
        borrow.setStation(0);
        borrowMapper.insert(borrow);
        return RespBean.success();
    }

    /**
     * 通过条件查询
     *
     * @param username 用户名
     * @param bookName 书名
     * @param current  当前页数
     * @return 查询结果
     */
    @Override
    public RespBean queryByCondition(String username, String bookName, String author, Integer current) {
        if (Objects.isNull(bookName)) bookName = "";
        if (Objects.isNull(author)) author = "";
        if (Objects.isNull(current)) current = 1;
        //查询借阅
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        queryWrapper.like("book_name", bookName);
        queryWrapper.like("book_name", author);
        Page<Borrow> page = borrowMapper.selectPage(new Page<>(current, 10), queryWrapper);
        return RespBean.success(page);
    }

    /**
     * 管理员添加借阅
     *
     * @param borrow 借阅实体
     * @return 结果
     */
    @Override
    public RespBean add(Borrow borrow) {
        borrowMapper.insert(borrow);
        return RespBean.success();
    }

    /**
     * 用户请求归还图书
     *
     * @param id 借阅id
     * @return 信息
     */
    @Override
    public RespBean requestRepaid(Integer id) {
        borrowMapper.requestRepaid(id);
        return RespBean.success();
    }

    /**
     * 用户撤销操作
     *
     * @param id 书籍id
     * @return 信息
     */
    @Override
    public RespBean cancel(Integer id) {
        Borrow borrow = borrowMapper.selectById(id);
        Integer station = borrow.getStation();
        //当前状态是申请借阅
        if (station == 0) {
            borrowMapper.cancelLend(id);
        } else {
            //当前状态是申请归还
            borrowMapper.cancelRepaid(id);
        }
        return RespBean.success();
    }

    /**
     * 管理员同意
     * @param id 借阅id
     * @param station 状态
     * @return 信息
     */
    @Override
    public RespBean agree(Integer id, Integer station) {
        if (station==0){
            //生成借出日期
            Date date = new Date();
            borrowMapper.agreeLend(id, date);
        }else if(station==2){
            //生成归还日期
            Date date = new Date();
            borrowMapper.agreeRepaid(id, date);
        }else{
            return RespBean.error(RespBeanEnum.ERROR);
        }
        return RespBean.success();
    }
}
