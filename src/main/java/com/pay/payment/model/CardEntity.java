package com.pay.payment.model;

public class CardEntity {

    private long pan;
    private String expiry;
    private String cvv;
    
    public CardEntity(long pan, String expiry, String cvv) {
        this.pan = pan;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    public long getPan() {
        return pan;
    }

    public void setPan(long pan) {
        this.pan = pan;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    

    
}
