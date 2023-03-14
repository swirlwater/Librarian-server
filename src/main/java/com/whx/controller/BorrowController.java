package com.whx.controller;


import com.whx.pojo.Borrow;
import com.whx.pojo.LoginUser;
import com.whx.service.IBorrowService;
import com.whx.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 *  借阅前端控制器
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@RestController
@RequestMapping("/borrow")
@Api("BorrowController")
public class BorrowController {

    @Autowired
    private IBorrowService borrowService;

    @GetMapping("/requestBorrow")
    @ApiOperation("用户请求借阅")
    public RespBean requestBorrow(Integer id){
        return borrowService.requestBorrow(id);
    }

    @PostMapping("/add")
    @ApiOperation("管理员添加借阅")
    public RespBean add(@RequestBody Borrow borrow){
        return borrowService.add(borrow);
    }

    @GetMapping("/agreeLend")
    @ApiOperation("管理员同意借阅")
    public RespBean agreeLend(Integer id){
        return borrowService.agreeLend(id);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除借阅")
    public RespBean delete(@RequestParam("ids") Integer[] ids){
        borrowService.removeByIds(Arrays.asList(ids));
        return RespBean.success();
    }

    @GetMapping("/query")
    @ApiOperation("管理员查看借阅")
    public RespBean query(String username,String bookName,String author,Integer current){
        return borrowService.queryByCondition(username,bookName,author,current);
    }

    @GetMapping("/queryWithUser")
    @ApiOperation("用户查看借阅")
    public RespBean queryWithUser(String bookName,String author,Integer current){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getUsername();
        return borrowService.queryByCondition(username,bookName,author,current);
    }

    @GetMapping("/requestRepaid")
    @ApiOperation("用户申请归还图书")
    public RespBean requestRepaid(Integer id){
        return borrowService.requestRepaid(id);
    }

    @GetMapping("/agreeRepaid")
    @ApiOperation("管理员同意归还图书")
    public RespBean agreeRepaid(Integer id){
        return borrowService.agreeRepaid(id);
    }
}
