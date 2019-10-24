package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Trade;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.ssm.tpssystem.domain.Interaction;

import java.util.List;
import java.util.Map;

import java.math.BigDecimal;
import java.util.List;

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

    public void addTrade(Trade trade);
    public Trade findTradeById(Integer id);


    @Select("SELECT * FROM TRADE WHERE ID = #{id}")
    Trade queryById(@Param("id") Integer id);

    /*
     * select by creator_id and product_id and price
     */
    @Select("SELECT * FROM TRADE " +
            "WHERE CREATOR_ID = #{creator_id} AND PRODUCT_ID = #{product_id} AND PRICE = #{price}")
    List<Trade> selectByCreator_idAndPrice(@Param("creator_id") Integer creator_id
            , @Param("product_id") Integer product_id
            , @Param("price") BigDecimal price);

    /*
     * select by creator_id and product_id
     */
    @Select("SELECT * FROM TRADE " +
            "WHERE CREATOR_ID = #{creator_id} AND PRODUCT_ID = #{product_id} ")
    List<Trade> selectByCreator_id(@Param("creator_id") Integer creator_id
            , @Param("product_id") Integer product_id);


    /*
     * select by relative_id and product_id and price
     */
    @Select("SELECT * FROM TRADE " +
            "WHERE RELATIVE_ID = #{relative_id} AND PRODUCT_ID = #{product_id} AND PRICE = #{price}")
    List<Trade> selectByRelative_idAndPrice(@Param("relative_id") Integer relative_id
            , @Param("product_id") Integer product_id
            ,@Param("price") BigDecimal price);

    /*
     * select by relative_id and product_id
     */
    @Select("SELECT * FROM TRADE " +
            "WHERE RELATIVE_ID = #{relative_id} AND PRODUCT_ID = #{product_id} ")
    List<Trade> selectByRelative_id(@Param("relative_id") Integer relative_id
            , @Param("product_id") Integer product_id);


    /*
     * update transaction after autoMatched
     * set match_id only
     */
    @Update("UPDATE TRADE " +
            "SET MATCH_ID = #{match_id} " +
            "WHERE ID = #{id}")
    int updateAfterMatched(@Param("match_id") Integer match_id
            ,@Param("id") Integer id);


    @Insert("INSERT INTO TRADE(CREATOR_ID,RELATIVE_ID,MATCH_ID,PRODUCT_ID,PRICE,ORIGIN_ID) " +
            "VALUES(#{creator_id},#{relative_id},#{match_id},#{product_id},#{price},#{origin_id})")
    int insertCurrentTrade(Trade trade);



}
