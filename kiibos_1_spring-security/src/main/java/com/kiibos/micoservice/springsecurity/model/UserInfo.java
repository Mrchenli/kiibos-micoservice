package com.kiibos.micoservice.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @ClassName UserInfo
 * @Description TODO
 * @Author cl
 * @Date 2019/2/27 下午2:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable, UserDetails {


    private static final long serialVersionUID = 1L;
    private String username;//用户名
    private String password;//密码
    private String role;//角色
    private boolean accountNonExpired;//账号是否过期
    private boolean accountNonLocked;//账号是否被锁
    private boolean credentialsNonExpired;//认证是否过期
    private boolean enabled;//是否删除


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
