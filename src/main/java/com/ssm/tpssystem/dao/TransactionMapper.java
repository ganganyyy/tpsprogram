package com.ssm.tpssystem.dao;



import com.ssm.tpssystem.domain.Transaction;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;
import com.ssm.tpssystem.domain.Transaction;

@Mapper
@Repository
public interface TransactionMapper {

    Integer selectLatestId(@Param("tradeId") Integer tradeId);

    Integer modify(Transaction transaction);

    int insertOneTransaction(Transaction transaction);
    public boolean addTransaction(Transaction transaction);

    //update interaction which is the newest trade status
    @Update("UPDATE TRANSACTION " +
            "SET INTERACTION_ID = #{interaction_id} " +
            "WHERE TRADE_ID = #{trade_id}")
    int updateInteraction_id(@Param("trade_id") Integer trade_id
            ,@Param("interaction_id") Integer interaction_id);


    @Insert("INSERT INTO TRANSACTION VALUES(#{trade_id},#{interaction_id})")
    int insert(@Param("trade_id") Integer trade_id
            ,@Param("interaction_id") Integer interaction_id);

    //query the newest interaction of trade
    @Select("SELECT INTERACTION_ID FROM TRANSACTION WHERE TRADE_ID = #{trade_id}")
    Integer queryInteractionId(@Param("trade_id") Integer trade_id);

}
