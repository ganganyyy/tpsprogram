package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.InteractionMapper;
import com.ssm.tpssystem.dao.TradeMapper;
import com.ssm.tpssystem.dao.TransactionMapper;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private InteractionMapper interactionMapper;


    @Override
    public List<Map<String, String>> getAllTradesByCreatorId(Integer creatorId) {
         return tradeMapper.selectAllByCreatorId(creatorId);
    }

    @Override
    public List<Map<String, String>> getAllTradesForSales() {
        return tradeMapper.selectAllBySales();
    }
    @Override
    public List<Map<String, String>> getAllTrades(Integer creator_id) {
        return tradeMapper.selectAll(creator_id);
    }

    @Override
    public Boolean updateTrade(Integer tradeId, Double price) {
        //1.update price
        int result= tradeMapper.updateForPrice(tradeId,price);
        //2.find latest interacitonId  +1
        int interactionId=transactionMapper.selectLatestId(tradeId)+1;
        //3.new interaction
        Interaction interaction=new Interaction();
        interaction.setCreate_time(new Date());
        interaction.setInteraction_id(interactionId);
        interaction.setTrade_id(tradeId);
        interaction.setVersion(1);
        int newIn=interactionMapper.insertOneInteraction(interaction);
        //4.modify transaction
        Transaction transaction=new Transaction();
        transaction.setTrade_id(tradeId);
        //TODO:increment interaction_id
        transaction.setInteraction_id(interaction.getId());
        int modi=transactionMapper.modify(transaction);
        if(result>0&&modi>0&&newIn>0){
            return true;
        }
        return false;
    }


}
