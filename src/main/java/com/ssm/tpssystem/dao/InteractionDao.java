package com.ssm.tpssystem.dao;

public class InteractionDao {
    private Integer id;
    private Integer interaction_id;
    private Integer version;
    private String reject_code;
    private String reject_reason;
    private Integer transaction_id;

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

    public String getReject_code() {
        return reject_code;
    }

    public void setReject_code(String reject_code) {
        this.reject_code = reject_code;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }
}
