package com.ssm.tpssystem.domain;

public class Transaction {
    private Integer trade_id;
    private Integer Interaction_id;

    public Integer getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Integer trade_id) {
        this.trade_id = trade_id;
    }

    public Integer getInteraction_id() {
        return Interaction_id;
    }

    public void setInteraction_id(Integer Interaction_id) {
        this.Interaction_id = Interaction_id;
    }
}
