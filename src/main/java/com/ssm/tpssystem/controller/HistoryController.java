package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.RestResult;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.service.HistoryService;
import com.ssm.tpssystem.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private ResultGenerator resultGenerator;

    @RequestMapping(value = "/historyOfTrade", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public RestResult displayHistoryOfTrade(HttpServletResponse httpServletResponse,
                                            @RequestBody Trade trade){

        Trade tempTrade = historyService.findTradeById(trade.getId());
        System.out.println("111");
        System.out.println(tempTrade.getOrigin_id());
        List<Interaction> list = historyService.findInteractionByTrade(tempTrade);
        while (tempTrade.getOrigin_id() != null){
            tempTrade = historyService.findTradeById(tempTrade.getOrigin_id());
            for(Interaction interaction:historyService.findInteractionByTrade(tempTrade)){
                list.add(interaction);
            }
        }
        return resultGenerator.getSuccessResult(list);
    }
}
