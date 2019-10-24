package com.ssm.tpssystem.domain;

import java.sql.Timestamp;

public class Interaction {
    private Integer id;
    private Integer interaction_id;
    private Integer version;
    private Integer reject_code;
    private String reject_reason;
    private Integer trade_id;
    private Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInteraction_id() {
        return interaction_id;
    }

    public void setInteraction_id(Integer interaction_id) {
        this.interaction_id = interaction_id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getReject_code() {
        return reject_code;
    }

    public void setReject_code(Integer reject_code) {
        this.reject_code = reject_code;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public Integer getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Integer trade_id) {
        this.trade_id = trade_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
