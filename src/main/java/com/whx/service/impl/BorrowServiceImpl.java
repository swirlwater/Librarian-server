package com.whx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whx.mapper.BorrowMapper;
import com.whx.pojo.Borrow;
import com.whx.service.IBorrowService;
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

    /**
     * 添加借阅
     * @param borrow 借阅实体
     */
    @Override
    public void add(Borrow borrow) {
        borrowMapper.insert(borrow);
    }

    /**
     * 通过条件查询
     * @param username 用户名
     * @param bookName 书名
     * @param current 当前页数
     * @return 查询结果
     */
    @Override
    public RespBean queryByCondition(String username, String bookName, Integer current) {
        //查询借阅
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        queryWrapper.like("book_name",bookName);
        Page<Borrow> page = borrowMapper.selectPage(new Page<>(current,10),queryWrapper);
        return RespBean.success(page);
    }
}
