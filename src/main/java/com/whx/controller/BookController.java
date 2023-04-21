package com.whx.controller;


import com.whx.pojo.Book;
import com.whx.service.IBookService;
import com.whx.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/add")
    @PreAuthorize("@ex.hasAuthority('sys:book:bookManage')")
    @ApiOperation("添加图书")
    public RespBean add(@RequestBody Book book){
        bookService.add(book);
        return RespBean.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@ex.hasAuthority('sys:book:bookManage')")
    @ApiOperation("删除图书")
    public RespBean delete(@RequestParam("ids") Integer[] ids){
        bookService.removeByIds(Arrays.asList(ids));
        return RespBean.success();
    }

    @PutMapping("/update")
    @PreAuthorize("@ex.hasAuthority('sys:book:bookManage')")
    @ApiOperation("修改图书")
    public RespBean update(@RequestBody Book book){
        bookService.updateById(book);
        return RespBean.success();
    }

    @GetMapping("/query")
    @PreAuthorize("@ex.hasAuthority('sys:book:bookSearch')")
    @ApiOperation("按条件查询图书")
    public RespBean query(String bookName,String author,Long currentPage){
        return bookService.queryByCondition(bookName,author,currentPage);
    }
}
