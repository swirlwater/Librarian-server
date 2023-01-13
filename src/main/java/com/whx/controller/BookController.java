package com.whx.controller;


import com.whx.pojo.Book;
import com.whx.service.IBookService;
import com.whx.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 *  书籍前端控制器
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@RestController
@RequestMapping("/book")
@Api("BookController")
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     * 添加图书
     * @param book 图书信息
     * @return 结果
     */
    @PostMapping("/add")
    @ApiOperation("添加图书")
    public RespBean add(Book book){
        bookService.add(book);
        return RespBean.success();
    }

    /**
     * 删除图书
     * @return 结果
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除图书")
    public RespBean delete(Integer[] ids){
        bookService.removeByIds(Arrays.asList(ids));
        return RespBean.success();
    }

    /**
     * 修改图书信息
     * @param book 图书实体
     * @return 结果
     */
    @PutMapping("/update")
    @ApiOperation("修改图书")
    public RespBean update(Book book){
        bookService.updateById(book);
        return RespBean.success();
    }

    /**
     * 按条件查询图书信息
     * @return 符合条件图书信息
     */
    @GetMapping("/query")
    @ApiOperation("按条件查询图书")
    public RespBean query(String bookName,String author,Long current){
        return bookService.queryByCondition(bookName,author,current);
    }
}
