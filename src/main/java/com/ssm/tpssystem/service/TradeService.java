package com.ssm.tpssystem.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TradeService {
    List<Map<String, String>> getAllTrades(Integer creator_id);
    List<Map<String,String>> getAllTradesByCreatorId(Integer creatorId);
    List<Map<String,String>> getAllTradesForSales();
    Boolean updateTrade(Integer transactionId,Double price);
}
