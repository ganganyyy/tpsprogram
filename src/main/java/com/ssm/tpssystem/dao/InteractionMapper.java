package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Interaction;

import com.ssm.tpssystem.domain.Trade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ssm.tpssystem.domain.Trade;




import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface InteractionMapper {
    public void addInteraction(Interaction interaction);
    public List<Map<Object,Object>> findInteractionByTrade(Trade trade);
    int insertOneInteraction(Interaction interaction);


    /*
     *Creat new interaction every time a transaction is executed between RS and DS.
     * RS:Requesting System
     * DS:Destination System
     */
    @Insert("INSERT INTO INTERACTION(INTERACTION_ID,VERSION,TRADE_ID) " +
            "VALUES(#{interaction_id},#{version},#{trade_id})")
    Integer insertInteraction(Interaction interaction);

    //query current interaction of trade
    @Select("SELECT MAX(ID) FROM INTERACTION WHERE TRADE_ID = #{trade_id}")
    Integer queryMaxId(@Param("trade_id") Integer trade_id);

    // query current interaction of a trade by id
    @Select("SELECT * FROM INTERACTION WHERE ID = #{id}")
    Interaction queryById(@Param("id") Integer id);
}
