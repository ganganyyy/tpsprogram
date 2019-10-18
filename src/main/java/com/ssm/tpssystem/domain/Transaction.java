package com.ssm.tpssystem.domain;

import java.math.BigDecimal;

public class Transaction {
    private Integer id;
    private Integer creator_id;
    private Integer relative_id;
    private Integer match_id;
    private Integer interaction_id;
    private Integer cusip_id;
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public Integer getRelative_id() {
        return relative_id;
    }

    public void setRelative_id(Integer relative_id) {
        this.relative_id = relative_id;
    }

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public Integer getInteraction_id() {
        return interaction_id;
    }

    public void setInteraction_id(Integer interaction_id) {
        this.interaction_id = interaction_id;
    }

    public Integer getCusip_id() {
        return cusip_id;
    }

    public void setCusip_id(Integer cusip_id) {
        this.cusip_id = cusip_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
