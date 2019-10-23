package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.RestResult;
import com.ssm.tpssystem.domain.User;
import com.ssm.tpssystem.service.TradeService;
import com.ssm.tpssystem.service.UserService;
import com.ssm.tpssystem.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TraderController {
    @Autowired
    private ResultGenerator resultGenerator;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/allTrades", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult getAllTrades(HttpSession session) {
       /* String username= (String) session.getAttribute("username");
        System.out.println("enter: "+username);*/
        //Integer creatorId=userService.findByUserName(username).getId();
        Integer creatorId= (Integer) session.getAttribute("Id");
        System.out.println("id: "+creatorId);
        //TODO:CREATOR_ID
        Map<String,Object>data=new HashMap<>();
        data.put("tradeList",tradeService.getAllTrades(1));
        return resultGenerator.getSuccessResult(data);
    }

    //@RequestMapping(value = "/allTradesForSales", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult getAllTradesForSales(){
        System.out.println("enter: sales");
        return resultGenerator.getSuccessResult(tradeService.getAllTradesForSales());
    }

    @RequestMapping(value = "/modifyTrade", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult modifyPrice(@RequestBody Map<String,String>data){
        String transactionIdS=data.get("tradeId");
        String priceS=data.get("price");
        if(transactionIdS==null||priceS==null){
            return resultGenerator.getFailResult("tradeId==null or price==null");
        }
        Integer tradeId=Integer.valueOf(transactionIdS);
        Double price=Double.valueOf(priceS);
        boolean flag=tradeService.updateTrade(tradeId,price);
        if(flag){
            return resultGenerator.getSuccessResult();
        }else{
            return resultGenerator.getFailResult("fail to modify");
        }

    }
}
