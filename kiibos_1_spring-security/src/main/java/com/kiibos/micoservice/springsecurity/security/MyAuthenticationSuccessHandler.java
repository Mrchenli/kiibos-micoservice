package com.kiibos.micoservice.springsecurity.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyAuthenticationSuccessHandler
 * @Description TODO
 * @Author cl
 * @Date 2019/2/27 下午3:01
 **/
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //如果是返回json
        Map<String,String> map=new HashMap<>();
        map.put("code", "200");
        map.put("msg", "登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(map));
        //如果是跳转到某个页面
        //new DefaultRedirectStrategy().sendRedirect(request, response, "/whoim");
    }
}
