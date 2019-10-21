package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.CreateTradeService;
import com.ssm.tpssystem.service.impl.CreateTradeServiceImpl;
import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/create")
public class CreateTradeController {

    @Autowired
    private CreateTradeService createTradeService;
    @RequestMapping("/createTrade")
    public void CreateTrade(HttpServletResponse httpServletResponse,
                                  @Param("transaction") Transaction transaction){
        transaction.setInteraction_id(1);
        Integer transaction_id = createTradeService.createTransaction(transaction);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Interaction interaction1 = new Interaction();
        Interaction interaction2 = new Interaction();

        interaction1.setTransaction_id(transaction_id);
        interaction2.setTransaction_id(transaction_id);

        interaction1.setInteraction_id(1);
        interaction2.setInteraction_id(1);

        interaction1.setVersion(1);
        interaction2.setVersion(2);

        interaction1.setCreate_time(timestamp);
        interaction2.setCreate_time(timestamp);

        createTradeService.createInteraction(interaction1);
        createTradeService.createInteraction(interaction2);


    }



}
