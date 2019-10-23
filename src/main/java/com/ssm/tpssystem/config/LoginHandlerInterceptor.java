/*
package com.ssm.tpssystem.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

   */
/* public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       *//*
*/
/* Integer Id = (Integer) request.getSession().getAttribute("Id");
        System.out.println("enter interceptor");
        if (Id == null) {
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else {
            return true;
        }*//*
*/
/*
    }*//*


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
*/
