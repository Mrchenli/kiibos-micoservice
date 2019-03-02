package com.kiibos.micoservice.springsecurity.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiibos.micoservice.springsecurity.model.UserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName JWTLoginFilter
 * @Description TODO
 * 验证用户名密码正确后，生成一个token,并将token返回给客户端
 * @Author cl
 * @Date 2019/2/28 下午1:52
 **/
public class JWTLoginFilter  extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login/form");
    }

    /**
     * @Author kiibos
     * @Description //接收并解析用户凭证
     * @Date 下午1:55 2019/2/28
     * @param request, response
     * @return org.springframework.security.core.Authentication
     **/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String paasword = obtainPassword(request);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        paasword,
                        new ArrayList<>())
        );
    }

    /**
     * @Author kiibos
     * @Description //用户登录成功后 这个方法会被调用，我们在这个方法里生成token
     * @Date 下午1:59 2019/2/28
     * @param req, res, chain, auth
     * @return void
     **/
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        UserInfo userIno = (UserInfo) auth.getPrincipal();
        String token = Jwts.builder()
                .setSubject(userIno.getUsername())//subject 把用户信息放进去
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
    }
}
