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
import java.util.Map;

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


        List<Map<Object,Object>> list = historyService.findInteractionByTrade(tempTrade);
        while (tempTrade.getOrigin_id() != null){
            tempTrade = historyService.findTradeById(tempTrade.getOrigin_id());
            for(Map<Object,Object> map:historyService.findInteractionByTrade(tempTrade)){
                list.add(map);
            }
        }
        return resultGenerator.getSuccessResult(list);
    }
}
