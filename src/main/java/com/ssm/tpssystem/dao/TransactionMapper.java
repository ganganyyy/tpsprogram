package com.ssm.tpssystem.dao;



import com.ssm.tpssystem.domain.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionMapper {
    public boolean addTransaction(Transaction transaction);

}
