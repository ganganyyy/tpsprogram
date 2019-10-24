package com.ssm.tpssystem.domain;

public class Transaction {
    private Integer trade_id;
    private Integer interaction_id;

    public Integer getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Integer trade_id) {
        this.trade_id = trade_id;
    }

    public Integer getInteraction_id() {
        return interaction_id;
    }

    public void setInteraction_id(Integer interaction_id) {
        this.interaction_id = interaction_id;
    }
}
