package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.RestResult;
import com.ssm.tpssystem.domain.ResultCode;
import com.ssm.tpssystem.domain.TradeId;
import com.ssm.tpssystem.domain.TradesId;
import com.ssm.tpssystem.service.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

    @Autowired(required = false)
    MatchServiceImpl matchService;

    @PostMapping("autoMatch")
    public RestResult autoMatch(@RequestBody TradeId tradeId){

        RestResult restResult = new RestResult();
        Integer result = 0;//default failure
        result = matchService.autoMatch(tradeId.getId());
        if(result ==1){
            restResult.setCode(ResultCode.SUCCESS.getCode());
            restResult.setMessage("Transaction is autoMatched successfully.");
        }
        else {
            restResult.setCode(ResultCode.FAIL.getCode());
            restResult.setMessage("Transaction is autoMatched failed.");
        }
        return restResult;
    }

    @PostMapping("MatchList")
    public RestResult listMatchTrades(@RequestBody TradeId tradeId){
        RestResult restResult = new RestResult();
        List<Map<String,Object>> trades = matchService.listMatchingTrades(tradeId.getId());
        if(trades==null||trades.size()==0){
            restResult.setCode(ResultCode.FAIL);
            restResult.setMessage("Request matchList failed.There is not any match trade.");
        }
        else {
            restResult.setCode(ResultCode.SUCCESS);
            restResult.setMessage("Request matchList successfully.");
            restResult.setData(trades);
        }

        return restResult;
    }

    @PostMapping("ManualMatch")
    public RestResult manualMatch(@RequestBody TradesId tradesId){
        Integer result = matchService.manualMatch(tradesId.getTradeId(),tradesId.getMatchTradeId());
        RestResult restResult = new RestResult();
        if(result ==1){
            restResult.setCode(ResultCode.SUCCESS.getCode());
            restResult.setMessage("Transaction is matched successfully.");
        }
        else {
            restResult.setCode(ResultCode.FAIL.getCode());
            restResult.setMessage("TransactionMatch is failed.");
        }
        return restResult;
    }
}
