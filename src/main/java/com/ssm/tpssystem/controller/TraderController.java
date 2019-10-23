package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.RestResult;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.domain.User;
import com.ssm.tpssystem.service.TradeService;
import com.ssm.tpssystem.service.UserService;
import com.ssm.tpssystem.utils.ResultGenerator;
import com.ssm.tpssystem.utils.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
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
    public RestResult getAllTrades(@RequestBody HashMap<String,String>retunData ,HttpSession session) {
       /* String username= (String) session.getAttribute("username");
        System.out.println("enter: "+username);*/
        //Integer creatorId=userService.findByUserName(username).getId();
        Integer creatorId= (Integer) session.getAttribute("Id");
        Integer userId=Integer.valueOf(retunData.get("userId"));
        System.out.println("id: "+userId);
        //TODO:CREATOR_ID
        Map<String,Object>data=new HashMap<>();
        List<Map<String,String>> tradeList=tradeService.getAllTrades(userId);
        for(Map trade:tradeList){
            String status= Transform.versionToStatus((Integer)trade.get("version"),(Integer)trade.get("reject_code"));
            trade.put("status",status);
        }
        data.put("tradeList",tradeList);
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
        BigDecimal price=BigDecimal.valueOf(Double.valueOf(priceS));
        boolean flag=tradeService.updateTrade(tradeId,price);
        if(flag){
            return resultGenerator.getSuccessResult();
        }else{
            return resultGenerator.getFailResult("fail to modify");
        }

    }
}
