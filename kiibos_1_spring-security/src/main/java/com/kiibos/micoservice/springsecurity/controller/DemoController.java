package com.kiibos.micoservice.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author cl
 * @Date 2019/2/27 下午1:45
 **/
@Controller
public class DemoController {

    @RequestMapping("/login")
    public String userLogin(){
        return "demo-sign";
    }

    @RequestMapping("/login-error")
    public String loginError(){
        return "login-error";
    }

    @RequestMapping
    @ResponseBody
    public String loginSuccess(){
        return "登录成功";
    }

}
