package com.kiibos.micoservice.springsecurity.security;

import com.kiibos.micoservice.springsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName MyAuthenticationProvider
 * @Description TODO
 * @Author cl
 * @Date 2019/2/27 下午2:11
 **/
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();//获取表单中输入的用户名
        String password = (String) authentication.getCredentials();//获取表单中输入的密码
        //根据用户名获取用户信息
        UserInfo userInfo = (UserInfo) userDetailsService.loadUserByUsername(userName);
        if(userInfo==null){
            throw new BadCredentialsException("用户名不存在");
        }
        if(!userInfo.getPassword().equals(password)){
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 这里直接改成retrun true;表示是支持这个执行
        return true;
    }
}
