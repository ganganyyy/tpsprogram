package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.InteractionMapper;
import com.ssm.tpssystem.dao.TradeMapper;
import com.ssm.tpssystem.dao.TransactionMapper;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.CreateTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Transactional
@Service
public class CreateTradeServiceImpl implements CreateTradeService {
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private InteractionMapper interactionMapper;

    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public Integer createInteraction(Interaction interaction) {
        interactionMapper.addInteraction(interaction);
        return interaction.getId();
    }

    @Override
    public Integer createTrade(Trade trade) {
        tradeMapper.addTrade(trade);
        return trade.getId();
    }

    @Override
    public boolean createTransaction(Transaction transaction) {

        boolean status = transactionMapper.addTransaction(transaction);
        return status;
    }

}
