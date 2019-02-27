package com.kiibos.micoservice.springsecurity.security;

import com.kiibos.micoservice.springsecurity.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyUserDetailService
 * @Description TODO
 * @Author cl
 * @Date 2019/2/27 下午2:08
 **/
@Component
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if("admin".equals(userName)){
            UserInfo userInfo=new UserInfo("admin",
                    "123456",
                    "ROLE_ADMIN",
                    true,
                    true,
                    true,
                    true);
            return userInfo;
        }
        return null;
    }
}
