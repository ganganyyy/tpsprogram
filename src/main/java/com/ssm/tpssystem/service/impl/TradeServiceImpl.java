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
        int interactionIdForTrade=transactionMapper.selectLatestId(tradeId)+1;
        int interactionIdForSale=transactionMapper.selectLatestId(preSale.getId())+1;
        //3.new interaction
        Integer interTra=0;
        for(int i=1;i<5;i++){
            Interaction interactionTra=new Interaction();
            Timestamp timestamp = new Timestamp(new Date().getTime());
            interactionTra.setCreate_time(timestamp);
            //TODO:interactionId为1还是原来
            interactionTra.setInteraction_id(interactionIdForTrade);
            interactionTra.setTrade_id(curId);
            interactionTra.setVersion(i);
            int newIn=interactionMapper.insertOneInteraction(interactionTra);
            interTra=interactionTra.getId();
        }
        Integer interSal=0;
        for(int i=1;i<5;i++){
            Interaction interactionSal=new Interaction();
            Timestamp timestamp = new Timestamp(new Date().getTime());
            interactionSal.setCreate_time(timestamp);
            //TODO:interactionId为1还是原来
            interactionSal.setInteraction_id(interactionIdForSale);
            interactionSal.setTrade_id(curSalId);
            interactionSal.setVersion(i);
            int newIn=interactionMapper.insertOneInteraction(interactionSal);
            interSal=interactionSal.getId();
        }


        //4.modify transaction
        Transaction transactionForTrade=new Transaction();
        transactionForTrade.setTrade_id(curId);
        transactionForTrade.setInteraction_id(interTra-1);
        //int modi=transactionMapper.modify(transaction);
        int newTrans=transactionMapper.insertOneTransaction(transactionForTrade);
        Transaction transactionForSale=new Transaction();
        transactionForSale.setTrade_id(curSalId);
        transactionForSale.setInteraction_id(interSal-1);
        //int modi=transactionMapper.modify(transaction);
        int newTrans2=transactionMapper.insertOneTransaction(transactionForSale);

        if(newTra>0&&newSal>0&&newTrans>0&&newTrans2>0&&modiMatch>0){
            return true;
        }
        return false;
    }


}
