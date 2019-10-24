package com.ssm.tpssystem.service;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.domain.Transaction;
import org.springframework.stereotype.Service;


@Service
public interface CreateTradeService {
    public Integer createInteraction(Interaction interaction);
    public Integer createTrade(Trade trade);
    public boolean createTransaction(Transaction transaction);
}
