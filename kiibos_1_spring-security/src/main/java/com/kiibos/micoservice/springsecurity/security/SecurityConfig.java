package com.kiibos.micoservice.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @ClassName SecurityConfig
 * @Description
 * 用户登录-->AuthenticationProcessingFilter
 * -->AuthenticationManager-->ProviderManager来获取用户信息
 * -->验证通过后会将用户的权限信息封装在一个User放到spring的全局缓存SecurityContextHolder中
 * 以备后面访问资源时候使用。
 * @Author cl
 * @Date 2019/2/27 下午1:39
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form") //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander)
                .permitAll()
                .and()
                .rememberMe()
                    //.rememberMeParameter("remember-me").userDetailsService(userDetailsService)
                    .tokenRepository(persistentTokenRepository())
                    //.tokenValiditySeconds(60)
                .and()
                .authorizeRequests()//.anyRequest().authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                .csrf().disable();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


}
