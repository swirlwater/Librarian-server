package com.whx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("helloController")
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("@ex.hasAuthority('system:hello:list')")
    @ApiOperation("hello")
    public String hello(){
        return "hello";
    }
}
