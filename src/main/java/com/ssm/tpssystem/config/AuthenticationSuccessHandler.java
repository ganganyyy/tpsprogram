/*
package com.ssm.tpssystem.config;

import com.ssm.tpssystem.utils.ResponseUtil;
import com.ssm.tpssystem.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ResultGenerator resultGenerator;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)throws IOException, ServletException{
        String username=((UserDetails)authentication.getPrincipal()).getUsername();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails)authentication.getPrincipal()).getAuthorities();
        List<String> list = new ArrayList<>();
        for(GrantedAuthority g : authorities){
            list.add(g.getAuthority());
        }

        //TODO:access

        //TODO:access control
        //String token= UUID.randomUUID().toString().replace("-","");
        //TODO:token should be saved on server,the way to implement:redis or jwt
        HashMap<String,String>data=new HashMap<>();
        data.put("duty",list.get(0));
        request.getSession().setAttribute("username",username);
        ResponseUtil.out(response,resultGenerator.getSuccessResult("Login Successful",data));
    }
}
*/
