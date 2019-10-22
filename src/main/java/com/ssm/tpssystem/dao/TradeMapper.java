package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Trade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TradeMapper {
    //select all trades made by a certain trader
    List<Map<String ,String>> selectAllByCreatorId(@Param("creatorId") Integer creator_id);

    //select all trades made by sales
    List<Map<String,String>>selectAllBySales();

    List<Map<String,String>>selectAll(@Param("creatorId") Integer creator_id);

    int updateForPrice(@Param("id") Integer tradeId,@Param("price") Double price);

    Trade selectOneById(@Param("id")Integer tradeId);

    int insertOneTrade(Trade trade);

    int updateMatchId(@Param("id")Integer tradeId,@Param("matchId")Integer matchId);
}
