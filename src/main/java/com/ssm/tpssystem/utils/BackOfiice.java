package com.ssm.tpssystem.utils;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.domain.Transaction;

import java.math.BigDecimal;

public class BackOfiice {

    private static double FLOAT_RATE = 0.15;
    public RejectResult BOExcecute(Trade trade,Trade changed){
        RejectResult rejectResult = new RejectResult();
        double tradePrice = trade.getPrice().doubleValue();
        double changedPrice = changed.getPrice().doubleValue();

        if( Math.abs(tradePrice-changedPrice) / tradePrice > FLOAT_RATE ) {
            rejectResult.setStatus("REJECTED");
            rejectResult.setReject_code(1);
            rejectResult.setReject_reason("Price changed too much");
            return rejectResult;
        }
        rejectResult.setStatus("ACCEPTED");

        return rejectResult;
    }

}
