package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.CreateTradeService;
import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/create")
public class CreateTradeController {

    @Autowired
    private CreateTradeService createTradeService;

    @RequestMapping(value = "/allTradesForSales", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void CreateTrade(HttpServletResponse httpServletResponse,
                                  @RequestBody Trade trade){

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



}
