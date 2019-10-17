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

import com.ssm.tpssystem.domain.RestResult;
import com.ssm.tpssystem.utils.ResultGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@CrossOrigin
@Controller
@RequestMapping("/client")
public class LoginController {
    @Autowired
    private ResultGenerator resultGenerator;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult login(HttpSession session) {
        return resultGenerator.getFailResult("登录失败");

    }
}


