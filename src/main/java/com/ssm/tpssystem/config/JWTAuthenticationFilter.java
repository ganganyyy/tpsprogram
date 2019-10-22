package com.ssm.tpssystem.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import sun.security.util.SecurityConstants;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private Integer tokenExpireTime;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,Integer tokenExpireTime) {
        super(authenticationManager);
        this.tokenExpireTime=tokenExpireTime;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint){
        super(authenticationManager,authenticationEntryPoint);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
        String header=request.getHeader(SecurityConstant.HEADER);
    }
}

