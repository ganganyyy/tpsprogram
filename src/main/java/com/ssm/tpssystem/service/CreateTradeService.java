package com.ssm.tpssystem.service;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Transaction;



public interface CreateTradeService {
    public boolean createInteraction(Interaction interaction);
    public Integer createTransaction(Transaction transaction);

}
