package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TradeMapper {
    public boolean addTrade(Trade trade);
}
