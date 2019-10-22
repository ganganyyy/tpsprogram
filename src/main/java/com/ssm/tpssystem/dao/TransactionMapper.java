package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TransactionMapper {

    Integer selectLatestId(@Param("tradeId") Integer tradeId);

    Integer modify(Transaction transaction);

    int insertOneTransaction(Transaction transaction);

}
