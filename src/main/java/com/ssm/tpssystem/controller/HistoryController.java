package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.RestResult;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.service.HistoryService;
import com.ssm.tpssystem.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

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
        return resultGenerator.getSuccessResult(historyService.findInteractionByTrade(trade));
    }
}
