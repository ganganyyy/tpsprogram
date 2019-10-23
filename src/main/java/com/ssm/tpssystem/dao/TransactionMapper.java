package com.ssm.tpssystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

}
