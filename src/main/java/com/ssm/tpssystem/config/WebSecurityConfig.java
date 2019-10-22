package com.ssm.tpssystem.config;

import com.ssm.tpssystem.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailHandler failHandler;
    @Override
    protected  void configure(HttpSecurity http)throws Exception{
        System.out.println("enter config");
        //configuration strategy
        http.csrf().disable();
        http.authorizeRequests().and()
        //表单登录方式
                .formLogin()
                .permitAll()
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
        //any request
               //Request()
        //Identity authentication required
                //.authenticated()
                .and()
        //close Cross-Site Request Forgery
                .csrf().disable()
        //front and rear end separation:JWT no need for session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),7));

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //encode
        auth.userDetailsService(userDetailsService).passwordEncoder(new CustomePasswordEncoder());
    }


}
