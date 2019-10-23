package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Interaction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssm.tpssystem.domain.Trade;




import java.util.List;

@Mapper
@Repository
public interface InteractionMapper {
    public void addInteraction(Interaction interaction);
    public List<Interaction> findInteractionByTrade(Trade trade);
    int insertOneInteraction(Interaction interaction);
}
