package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.InteractionMapper;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private InteractionMapper interactionMapper;

    @Override
    public List<Interaction> findInteractionByTrade(Trade trade) {
        List<Interaction> list = interactionMapper.findInteractionByTrade(trade);
        return list;
    }
}
