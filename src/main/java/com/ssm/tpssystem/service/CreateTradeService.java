package com.ssm.tpssystem.service;

import java.math.BigDecimal;

public interface CreateTradeService {
    public boolean createInteraction(Integer interaction_id, Integer version, String reject_code,
                                             String reject_reason, Integer transaction_id);
    public boolean createTransaction(Integer creator_id, Integer relative_id, Integer cusip_id, BigDecimal price
            , Integer interaction_id);

}
