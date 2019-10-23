package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.*;
import com.ssm.tpssystem.service.CreateTradeService;
import com.ssm.tpssystem.service.ProductService;
import com.ssm.tpssystem.service.UserService;
import com.ssm.tpssystem.utils.ResultGenerator;
import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/create")
public class CreateTradeController {

    @Autowired
    private CreateTradeService createTradeService;
    @Autowired
    private ResultGenerator resultGenerator;


    @RequestMapping(value = "/createTrade", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void CreateTrade(HttpServletResponse httpServletResponse,
                            @RequestBody Trade trade, HttpSession session){

        Integer creator_id = (Integer)session.getAttribute("Id");
        trade.setCreator_id(creator_id);
        Integer trade_id = createTradeService.createTrade(trade);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Interaction interaction = new Interaction();

        interaction.setVersion(1);
        interaction.setInteraction_id(1);
        interaction.setTrade_id(trade_id);
        interaction.setCreate_time(timestamp);

        createTradeService.createInteraction(interaction);

        timestamp = new Timestamp(new Date().getTime());
        interaction.setVersion(2);
        interaction.setCreate_time(timestamp);
        Integer interaction_id = createTradeService.createInteraction(interaction);

        Transaction transaction = new Transaction();
        transaction.setTrade_id(trade_id);
        transaction.setInteraction_id(interaction_id);
        createTradeService.createTransaction(transaction);
    }

    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/displayProduct", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult DisplayProduct(HttpServletResponse httpServletResponse){
        return resultGenerator.getSuccessResult(productService.findAllProduct());
    }

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/displaySales", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult DisplaySales(HttpServletResponse httpServletResponse){
        return resultGenerator.getSuccessResult(userService.findAllSales());
    }

}
