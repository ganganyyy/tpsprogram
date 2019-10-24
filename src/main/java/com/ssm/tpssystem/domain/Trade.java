package com.ssm.tpssystem.domain;

import java.math.BigDecimal;

public class Trade {
    private Integer id;
    private Integer creator_id;
    private Integer relative_id;
    private Integer match_id;
    private Integer product_id;
    private BigDecimal price;
    private Integer origin_id;

    public Trade(){

    }
    public Trade(Integer id, Integer creator_id, Integer relative_id, Integer match_id, Integer product_id, BigDecimal price, Integer origin_id) {
        this.id = id;
        this.creator_id = creator_id;
        this.relative_id = relative_id;
        this.match_id = match_id;
        this.product_id = product_id;
        this.price = price;
        this.origin_id = origin_id;
    }

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

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(Integer origin_id) {
        this.origin_id = origin_id;
    }
}
