package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.*;
import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.service.MatchService;
import com.ssm.tpssystem.utils.BackOfiice;
import com.ssm.tpssystem.utils.RejectResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired(required = false)
    TransactionMapper transactionMapper;
    @Autowired(required = false)
    TradeMapper tradeMapper;
    @Autowired(required = false)
    InteractionMapper interactionMapper;
    @Autowired(required = false)
    ProductMapper productMapper;
    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public Integer autoMatch(Integer tradeId){
        Trade trade = tradeMapper.queryById(tradeId);
        if(trade==null){
            return -2;
        }
        if(trade.getMatch_id()==null){
            List<Trade> SalesTradeList = tradeMapper.selectByCreator_idAndPrice(trade.getRelative_id(),trade.getProduct_id(),trade.getPrice());
            if(SalesTradeList == null||SalesTradeList.size() == 0){
                List<Trade> TraderTradeList = tradeMapper.selectByRelative_idAndPrice(trade.getCreator_id(),trade.getProduct_id(),trade.getPrice());
                //if tradeList is empty,autoMatch unsuccessfully
                if(TraderTradeList == null|| TraderTradeList.size() == 0)
                    return 0;

                else {

                    for(Trade tradeItem:TraderTradeList){
                        Integer matchedInteractionId = transactionMapper.queryInteraction(tradeItem.getId());
                        Interaction matchedInteraction = interactionMapper.queryById(matchedInteractionId);
                        Integer version = matchedInteraction.getVersion();
                        if(version != 2){
                            //version !=2 means the trade is not pending (can not match)
                            continue;
                        }

                        //version==2 ,autoMatch success
                        //the current tradeItem is the matched trade
                        else {

                            //create new interaction for the matched trade
                            // version from 2 to 3 means matched successfully
                            matchedInteraction.setVersion(3);
                            interactionMapper.insertInteraction(matchedInteraction);

                            //create new interaction for the current trade
                            Integer interactionId = transactionMapper.queryInteraction(trade.getId());
                            Interaction interaction = interactionMapper.queryById(interactionId);
                            interaction.setVersion(3);
                            interactionMapper.insertInteraction(interaction);

                            //update current transacton for the two trades
                            Integer maxId = interactionMapper.queryMaxId(trade.getId());
                            transactionMapper.updateInteraction_id(trade.getId(),maxId);
                            maxId = interactionMapper.queryMaxId(tradeItem.getId());
                            transactionMapper.updateInteraction_id(tradeItem.getId(),maxId);

                            //update match_id for the two matched trade
                            tradeMapper.updateAfterMatched(tradeItem.getId(),trade.getId());
                            tradeMapper.updateAfterMatched(trade.getId(),tradeItem.getId());

                            setTerminalStatus(trade);
                            setTerminalStatus(tradeItem);

                            //return 1 represents autoMatch successfully
                            return 1;
                        }
                    }
                }
            }

            else {

                for(Trade tradeItem:SalesTradeList){
                    Integer matchedInteractionId = transactionMapper.queryInteraction(tradeItem.getId());
                    Interaction matchedInteraction = interactionMapper.queryById(matchedInteractionId);
                    Integer version = matchedInteraction.getVersion();
                    if(version != 2){
                        //version !=2 means the trade is not pending (can not match)
                        continue;
                    }
                    //version==2 ,autoMatch success
                    //the current tradeItem is the matched trade
                    else {

                        //create new interaction for the matched trade
                        // version from 2 to 3 means matched successfully
                        matchedInteraction.setVersion(3);
                        interactionMapper.insertInteraction(matchedInteraction);

                        //create new interaction for the current trade
                        Integer interactionId = transactionMapper.queryInteraction(trade.getId());
                        Interaction interaction = interactionMapper.queryById(interactionId);
                        interaction.setVersion(3);
                        interactionMapper.insertInteraction(interaction);

                        //update current transaction for the two trades
                        Integer maxId = interactionMapper.queryMaxId(trade.getId());
                        transactionMapper.updateInteraction_id(trade.getId(),maxId);
                        maxId = interactionMapper.queryMaxId(tradeItem.getId());
                        transactionMapper.updateInteraction_id(tradeItem.getId(),maxId);

                        //update match_id for the two matched trade
                        tradeMapper.updateAfterMatched(tradeItem.getId(),trade.getId());
                        tradeMapper.updateAfterMatched(trade.getId(),tradeItem.getId());


                        setTerminalStatus(trade);
                        setTerminalStatus(tradeItem);

                        //return 1 represents autoMatch successfully
                        return 1;
                    }
                }
            }
            //when 'return 0' there is executed,autoMatching is unsuccessful
            return 0;
        }
        else {
            return -1;
        }
    }

    //correct price of salesTrade equal to traderTrade
    @Override
    public Integer manualMatch(Integer tradeId,Integer matchTradeId) {

        Trade tempTrade = tradeMapper.queryById(tradeId);

        if(tempTrade==null)
            return -2;

        String tempDuty = userMapper.queryDutyById(tempTrade.getCreator_id());
        Trade traderTrade = new Trade();
        Trade originSalesTrade = new Trade();
        if(tempDuty.equals("T")||tempDuty.equals("t")){
            traderTrade = tradeMapper.queryById(tradeId);
            originSalesTrade = tradeMapper.queryById(matchTradeId);
            if(traderTrade==null||originSalesTrade==null)
                return -2;
            if(traderTrade.getMatch_id()!=null||originSalesTrade.getMatch_id()!=null)
                return -1;
        }
        else {
            traderTrade = tradeMapper.queryById(matchTradeId);
            originSalesTrade = tradeMapper.queryById(tradeId);
            if(traderTrade==null||originSalesTrade==null)
                return -2;
            if(traderTrade.getMatch_id()!=null||originSalesTrade.getMatch_id()!=null)
                return -1;
        }

        Integer originSalesInteractionId = transactionMapper.queryInteraction(traderTrade.getId());
        Interaction originSalesInteraction = interactionMapper.queryById(originSalesInteractionId);

        //set new salesTrade whose price is corrected equal to traderTrade
        Trade currentSalesTrade = new Trade();

        //insert current salesTrade with match_id
        currentSalesTrade.setCreator_id(originSalesTrade.getCreator_id());
        currentSalesTrade.setRelative_id(originSalesTrade.getRelative_id());
        currentSalesTrade.setMatch_id(traderTrade.getId());
        currentSalesTrade.setProduct_id(originSalesTrade.getProduct_id());
        currentSalesTrade.setPrice(traderTrade.getPrice());
        currentSalesTrade.setOrigin_id(originSalesTrade.getId());
        tradeMapper.insertCurrentTrade(currentSalesTrade);

        //set match_id for traderTrade
        List<Trade> SalesTradeList = tradeMapper.selectByCreator_idAndPrice(
                traderTrade.getRelative_id()
                ,traderTrade.getProduct_id()
                ,traderTrade.getPrice());
        if(SalesTradeList==null||SalesTradeList.size()==0){
            return 0;
        }
        else {
            for(Trade tradeItem:SalesTradeList){
                if(transactionMapper.queryInteraction(tradeItem.getId())!=null){
                    continue;
                }
                else{
                    //set the match_id of traderTrade
                    tradeMapper.updateAfterMatched(tradeItem.getId(),traderTrade.getId());
                    //create new interaction for the current trade
                    Integer interactionId = transactionMapper.queryInteraction(traderTrade.getId());
                    Interaction interaction = interactionMapper.queryById(interactionId);
                    interaction.setVersion(3);
                    interactionMapper.insertInteraction(interaction);

                    //create new interaction for the current matchedTrade
                    //correct trade_id and version only
                    Interaction currentSalesInteraction = new Interaction();
                    currentSalesInteraction.setTrade_id(tradeItem.getId());
                    currentSalesInteraction.setVersion(3);
                    currentSalesInteraction.setInteraction_id(originSalesInteraction.getInteraction_id());
                    interactionMapper.insertInteraction(currentSalesInteraction);

                    //update current transaction for the traderTrade
                    Integer maxId = interactionMapper.queryMaxId(traderTrade.getId());
                    transactionMapper.updateInteraction_id(traderTrade.getId(),maxId);

                    //insert current transaction for the salesTrade
                    maxId = interactionMapper.queryMaxId(tradeItem.getId());
                    transactionMapper.insert(tradeItem.getId(),maxId);

                    setTerminalStatus(traderTrade);
                    setTerminalStatus(tradeItem);
                    //return 1 represents manualMatch successfully
                    return 1;
                }
            }
        }

        //when 'return 0' there is executed,ManualMatching is unsuccessful
        return 0;
    }

    /*
     * every trade shows creatorName,price,cusip,time,status only.
     */
    @Override
    public List<Map<String, Object>> listMatchingTrades(Integer tradeId) {
        Trade trade = tradeMapper.queryById(tradeId);

        if(trade==null)
            return null;
        if(trade.getMatch_id()!=null)
            return null;

        List<Map<String,Object>> resultTradeList = new ArrayList<>();
        List<Trade> SalesTradeList = tradeMapper.selectByCreator_id(trade.getRelative_id(),trade.getProduct_id());
        if(SalesTradeList == null||SalesTradeList.size() == 0) {
            List<Trade> TraderTradeList = tradeMapper.selectByRelative_id(trade.getCreator_id(), trade.getProduct_id());
            if(TraderTradeList==null||TraderTradeList.size()==0)
                return null;
            else {
                //filter trades whose version = 2(status = pending)
                for (Trade tradeItem:TraderTradeList){
                    Integer matchedInteractionId = transactionMapper.queryInteraction(tradeItem.getId());
                    Interaction matchedInteraction = interactionMapper.queryById(matchedInteractionId);
                    Integer version = matchedInteraction.getVersion();
                    if(version != 2){
                        //version !=2 means the trade is not pending (can not match)
                        continue;
                    }
                    //' version==2 'means the current tradeItem could be matched trade
                    // add this trade into resultTradeList
                    //packing the trade to map format
                    else{
                        Integer id = tradeItem.getId();
                        Timestamp timestamp = matchedInteraction.getCreate_time();
                        String creatorName = userMapper.queryNameById(tradeItem.getCreator_id());
                        String cusip = productMapper.queryCusipById(tradeItem.getProduct_id());
                        BigDecimal price = tradeItem.getPrice();
                        String status = "PENDING";
                        Integer creatorId = tradeItem.getCreator_id();
                        Map<String,Object> tradeMap = new HashMap<>();
                        tradeMap.put("id",id);
                        tradeMap.put("creatorId",creatorId);
                        tradeMap.put("creatorName",creatorName);
                        tradeMap.put("cusip",cusip);
                        tradeMap.put("price",price);
                        tradeMap.put("status",status);
                        tradeMap.put("timestamp",timestamp);
                        //add to th resultList
                        resultTradeList.add(tradeMap);
                    }
                }

            }
        }
        else {
            //filter trades whose version = 2(status = pending)
            for (Trade tradeItem:SalesTradeList){
                Integer matchedInteractionId = transactionMapper.queryInteraction(tradeItem.getId());
                Interaction matchedInteraction = interactionMapper.queryById(matchedInteractionId);
                Integer version = matchedInteraction.getVersion();
                if(version != 2){
                    //version !=2 means the trade is not pending (can not match)
                    continue;
                }
                //' version==2 'means the current tradeItem could be matched trade
                // add this trade into tradeList
                else{
                    Integer id = tradeItem.getId();
                    Timestamp timestamp = matchedInteraction.getCreate_time();
                    String creatorName = userMapper.queryNameById(tradeItem.getCreator_id());
                    String cusip = productMapper.queryCusipById(tradeItem.getProduct_id());
                    BigDecimal price = tradeItem.getPrice();
                    String status = "PENDING";
                    Integer creatorId = tradeItem.getCreator_id();
                    Map<String,Object> tradeMap = new HashMap<>();
                    tradeMap.put("id",id);
                    tradeMap.put("creatorId",creatorId);
                    tradeMap.put("creatorName",creatorName);
                    tradeMap.put("cusip",cusip);
                    tradeMap.put("price",price);
                    tradeMap.put("status",status);
                    tradeMap.put("timestamp",timestamp);
                    //add to th resultList
                    resultTradeList.add(tradeMap);
                }
        }
        }
        return resultTradeList;
    }


    private Integer setTerminalStatus(Trade trade){
        BackOfiice backOfiice = new BackOfiice();
        RejectResult rejectResult = new RejectResult();
        Integer origin_id = trade.getOrigin_id();

        //the trade has never been corrected
        if(origin_id==null||origin_id==0){
           rejectResult= backOfiice.BOExcecute(trade,trade);
        }
        else {
            Trade originTrade = tradeMapper.queryById(origin_id);

            // originId !=null and originTrade==null,data error
            if(originTrade==null){
                return -1;
            }
            else {
                rejectResult = backOfiice.BOExcecute(trade,originTrade);
            }
        }
        if(rejectResult.getStatus().equals("REJECTED")){

            //create new interaction for the current trade
            Integer interactionId = transactionMapper.queryInteraction(trade.getId());
            Interaction interaction = interactionMapper.queryById(interactionId);
            interaction.setVersion(4);
            interaction.setReject_code(rejectResult.getReject_code());
            interaction.setReject_reason(rejectResult.getReject_reason());
            interactionMapper.insertInteraction(interaction);

            //update current transacton for the trade
            Integer maxId = interactionMapper.queryMaxId(trade.getId());
            transactionMapper.updateInteraction_id(trade.getId(),maxId);
            //return 1 represents rejected
            return 0;
        }
        else if(rejectResult.getStatus().equals("ACCEPTED")){

            //create new interaction for the current trade
            Integer interactionId = transactionMapper.queryInteraction(trade.getId());
            Interaction interaction = interactionMapper.queryById(interactionId);
            interaction.setVersion(4);
            interactionMapper.insertInteraction(interaction);

            //update current transacton for the trade
            Integer maxId = interactionMapper.queryMaxId(trade.getId());
            transactionMapper.updateInteraction_id(trade.getId(),maxId);
            //return 1 represents accept
            return 1;
        }
        else {
            // -1 means server internal error
            return -1;
        }
    }
}
