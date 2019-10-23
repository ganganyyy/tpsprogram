package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.InteractionMapper;
import com.ssm.tpssystem.dao.TradeMapper;
import com.ssm.tpssystem.dao.TransactionMapper;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    public Boolean updateTrade(Integer tradeId, BigDecimal price) {
        //1.update price
        //int result= tradeMapper.updateForPrice(tradeId,price);
        Trade preTrade=tradeMapper.selectOneById(tradeId);
        Trade curTrade=new Trade(null,preTrade.getCreator_id(),preTrade.getRelative_id(),null,
                preTrade.getProduct_id(),price,preTrade.getId());
        int newTra=tradeMapper.insertOneTrade(curTrade);
        Integer curId=curTrade.getId()-1;

        Trade preSale=tradeMapper.selectOneById(preTrade.getMatch_id());
        Trade curSale=new Trade(null,preSale.getCreator_id(),preSale.getRelative_id(),curId,
                preSale.getProduct_id(),price,preSale.getId());
        int newSal=tradeMapper.insertOneTrade(curSale);
        Integer curSalId=curSale.getId()-1;

        int modiMatch=tradeMapper.updateMatchId(curId,curSalId);

        //2.find latest interacitonId  +1
        int interactionId=transactionMapper.selectLatestId(tradeId)+1;
        //        //3.new interaction
        Interaction interaction=new Interaction();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        interaction.setCreate_time(timestamp);
        //TODO:interactionId为1还是原来的
        interaction.setInteraction_id(interactionId);
        interaction.setTrade_id(curId);
        interaction.setVersion(1);
        int newIn=interactionMapper.insertOneInteraction(interaction);
        //4.modify transaction
        Transaction transaction=new Transaction();
        transaction.setTrade_id(curId);
        transaction.setInteraction_id(interaction.getId()-1);
        //int modi=transactionMapper.modify(transaction);
        int newTrans=transactionMapper.insertOneTransaction(transaction);
        if(newTra>0&&newSal>0&&newIn>0&&newTrans>0&&modiMatch>0){
            return true;
        }
        return false;
    }


}
