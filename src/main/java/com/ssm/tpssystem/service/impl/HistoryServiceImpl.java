package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.InteractionMapper;
import com.ssm.tpssystem.dao.TradeMapper;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private InteractionMapper interactionMapper;
    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public Trade findTradeById(Integer id){
        return tradeMapper.findTradeById(id);
    }
    @Override
    public List<Map<Object,Object>> findInteractionByTrade(Trade trade) {

        List<Map<Object,Object>> list = interactionMapper.findInteractionByTrade(trade);


        return list;
    }
}
