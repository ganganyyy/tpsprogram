package com.ssm.tpssystem.service;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HistoryService {
    public List<Interaction> findInteractionByTrade(Trade trade);
}
