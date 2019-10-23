package com.ssm.tpssystem.utils;

public class RejectResult {
    private String status;
    private Integer reject_code;
    private String reject_reason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
