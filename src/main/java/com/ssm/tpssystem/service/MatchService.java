package com.ssm.tpssystem.service;

import java.util.List;
import java.util.Map;

public interface MatchService {

    public Integer autoMatch(Integer tradeId);

    public Integer manualMatch(Integer tradeId, Integer matchTradeId);

    //list all the trades waiting for matching referring to the trade.
    // creator name;cusip;price;status;timestamp
    public List<Map<String, Object>> listMatchingTrades(Integer tradeId);
}
