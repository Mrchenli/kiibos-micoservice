package com.kiibos.micoservice.springsecurity.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RbacServiceImpl
 * @Description TODO
 * @Author cl
 * @Date 2019/2/27 下午3:30
 **/
@Component("rbacService")
public class RbacServiceImpl implements RbacService{

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if(principal instanceof UserDetails){
            String userName = ((UserDetails) principal).getUsername();
            Set<String> urls = new HashSet<>();//数据库读取 //读取用户所拥有权限的所有url
            urls.add("/whoim");
            for (String url : urls){
                if(antPathMatcher.match(url,request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
