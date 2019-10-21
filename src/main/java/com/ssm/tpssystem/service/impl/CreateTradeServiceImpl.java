package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.InteractionMapper;
import com.ssm.tpssystem.dao.TransactionMapper;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.CreateTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateTradeServiceImpl implements CreateTradeService {
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private InteractionMapper interactionMapper;

    @Override
    public boolean createInteraction(Interaction interaction) {

        boolean status= interactionMapper.addInteraction(interaction);
        return status;
    }

    @Override
    public Integer createTransaction(Transaction transaction) {

        boolean status = transactionMapper.addTransaction(transaction);
        return transaction.getId();
    }

}
