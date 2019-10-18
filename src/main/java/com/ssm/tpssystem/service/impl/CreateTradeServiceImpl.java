package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.InteractionMapper;
import com.ssm.tpssystem.dao.TransactionMapper;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.CreateTradeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CreateTradeServiceImpl implements CreateTradeService {
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private InteractionMapper interactionMapper;

    @Override
    public boolean createInteraction(Integer interaction_id, Integer version, String reject_code,
                                     String reject_reason, Integer transaction_id) {
        Interaction interaction = new Interaction();

        interaction.setInteraction_id(interaction_id);
        interaction.setVersion(version);
        interaction.setReject_code(reject_code);
        interaction.setReject_reason(reject_reason);
        interaction.setTransaction_id(transaction_id);

        boolean status= interactionMapper.addInteraction(interaction);
        return status;
    }

    @Override
    public boolean createTransaction(Integer creator_id, Integer relative_id, Integer cusip_id,
                                             BigDecimal price, Integer interaction_id) {
        Transaction transaction = new Transaction();
        transaction.setCreator_id(creator_id);
        transaction.setRelative_id(relative_id);
        transaction.setCusip_id(cusip_id);
        transaction.setPrice(price);
        transaction.setInteraction_id(interaction_id);
        boolean status = transactionMapper.addTransaction(transaction);
        return status;
    }

}
