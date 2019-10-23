package com.ssm.tpssystem.domain;

public class Trade {
    private Integer id;
    private Integer creatorId;
    private Integer relativeId;
    private Integer matchId;
    private Integer productId;
    private Double price;
    private Integer originId;

    public Trade(){

    }

    public Trade(Integer id, Integer creatorId, Integer relativeId, Integer matchId, Integer productId, Double price, Integer originId) {
        this.id = id;
        this.creatorId = creatorId;
        this.relativeId = relativeId;
        this.matchId = matchId;
        this.productId = productId;
        this.price = price;
        this.originId = originId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Integer relativeId) {
        this.relativeId = relativeId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
