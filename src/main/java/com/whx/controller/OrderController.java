package com.whx.controller;


import com.whx.pojo.LoginUser;
import com.whx.pojo.Order;
import com.whx.service.IOrderService;
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
 *  订单前端控制器
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@RestController
@RequestMapping("/order")
@Api("OrderController")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/add")
    @ApiOperation("添加订单")
    public RespBean add(@RequestBody Order order){
        orderService.add(order);
        return RespBean.success();
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单")
    public RespBean delete(Integer[] ids){
        orderService.removeByIds(Arrays.asList(ids));
        return RespBean.success();
    }

    @PutMapping("/update")
    @ApiOperation("处理订单")
    public RespBean update(@RequestBody Order order){
        orderService.updateById(order);
        return RespBean.success();
    }

    @GetMapping("/query")
    @ApiOperation("管理员查询订单")
    public RespBean query(String username,String bookName,String author,Long current){
        return orderService.queryByCondition(username,bookName,author,current);
    }

    @GetMapping("/queryWithUser")
    @ApiOperation("用户查询订单")
    public RespBean queryWithUser(String bookName,String author,Long current){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getUsername();
        orderService.queryByCondition(username,bookName,author,current);
        return null;
    }
}
