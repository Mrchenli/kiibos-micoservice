package com.kiibos.micoservice.springsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author cl
 * @Date 2019/2/27 下午1:55
 **/
@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/whoim")
    public Object whoIm(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



}
