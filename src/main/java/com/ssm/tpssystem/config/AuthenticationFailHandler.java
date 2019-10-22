package com.ssm.tpssystem.config;

import com.ssm.tpssystem.utils.ResponseUtil;
import com.ssm.tpssystem.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ResultGenerator resultGenerator;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)throws IOException, ServletException {
        if(e instanceof UsernameNotFoundException||e instanceof BadCredentialsException){
            ResponseUtil.out(response,resultGenerator.getFailResult("wrong username or password"));
        }else if(e instanceof DisabledException){
            ResponseUtil.out(response,resultGenerator.getFailResult("Your account is disabled, please contact the with administrator"));
        }else{
            ResponseUtil.out(response,resultGenerator.getFailResult("other internal errors"));
        }

    }
}
