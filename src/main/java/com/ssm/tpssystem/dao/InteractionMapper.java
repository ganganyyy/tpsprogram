package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Interaction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InteractionMapper {
    public boolean addInteraction(Interaction interaction);

}
