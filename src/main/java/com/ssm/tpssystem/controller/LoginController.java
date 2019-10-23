package com.ssm.tpssystem.controller;


/*import com.test.mms.service.LoginServiceImpl;
import com.test.mms.utils.RandomValidateCodeUtil;
import com.test.mms.utils.ResultGenerator;
import com.test.mms.domain.RestResult;
import com.test.mms.domain.SessionJson;
import com.test.mms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;*/

import com.ssm.tpssystem.dao.UserMapper;
import com.ssm.tpssystem.domain.RestResult;
import com.ssm.tpssystem.domain.User;
import com.ssm.tpssystem.service.UserService;
import com.ssm.tpssystem.utils.ResultGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {
    @Autowired
    private ResultGenerator resultGenerator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult login(@RequestBody HashMap<String,String>data,HttpSession session) {
        System.out.println("enter");
        String username=data.get("username");
        String password=data.get("password");
        User user=userService.findUser(username,password);
        if(user==null){
            return resultGenerator.getFailResult("wrong username or password");
        }else{
            session.setAttribute("Id",user.getId());
            System.out.println("ID:"+session.getAttribute("Id"));
            Map<String,String> returnData=new HashMap<>();
            returnData.put("Id",user.getId().toString());
            returnData.put("duty",user.getDuty());
            return resultGenerator.getSuccessResult("login successful",returnData);
        }
    }


    public RestResult exit(HttpSession session){
        session.removeAttribute("Id");
        return resultGenerator.getSuccessResult("exit");
    }
}


