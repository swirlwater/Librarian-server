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

import java.util.List;

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

    @PostMapping("/add")
    @ApiOperation("添加借阅")
    public RespBean add(Borrow borrow){
        borrowService.add(borrow);
        return RespBean.success();
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除借阅")
    public RespBean delete(List<String> ids){
        borrowService.removeByIds(ids);
        return RespBean.success();
    }

    @GetMapping("/query")
    @ApiOperation("管理员查看借阅")
    public RespBean query(String username,String bookName,Integer current){
        return borrowService.queryByCondition(username,bookName,current);
    }

    @GetMapping("/queryWithUser")
    @ApiOperation("用户查看借阅")
    public RespBean queryWithUser(String bookName,Integer current){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getUsername();
        return borrowService.queryByCondition(username,bookName,current);
    }
}
