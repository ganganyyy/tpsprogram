package com.ssm.tpssystem.service;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HistoryService {
    public Trade findTradeById(Integer id);
    public List<Map<Object,Object>> findInteractionByTrade(Trade trade);
}
