package com.ssm.tpssystem.config;

import com.ssm.tpssystem.utils.ResponseUtil;
import com.ssm.tpssystem.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ResultGenerator resultGenerator;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)throws IOException, ServletException{
        String username=((UserDetails)authentication.getPrincipal()).getUsername();
        //TODO:access
        String duty="s";
        //TODO:access control
        String token= UUID.randomUUID().toString().replace("-","");
        //TODO:token should be saved on server,the way to implement:redis or jwt
        HashMap<String,String>data=new HashMap<>();
        data.put("token",token);
        data.put("duty",duty);
        ResponseUtil.out(response,resultGenerator.getSuccessResult("Login Successful",data));
    }
}
