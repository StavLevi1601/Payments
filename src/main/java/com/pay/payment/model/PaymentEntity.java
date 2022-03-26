package com.pay.payment.model;

import java.math.BigDecimal;

public class PaymentEntity {

    private long invoice;
    private BigDecimal amount;
    private String currency;
    private CardholderEntity cardholder;
    private CardEntity card;
    
    public PaymentEntity(long invoice, BigDecimal amount, String currency, CardholderEntity cardholder,
            CardEntity card) {
        this.invoice = invoice;
        this.amount = amount;
        this.currency = currency;
        this.cardholder = cardholder;
        this.card = card;
    }

    public long getInvoice() {
        return invoice;
    }

    public void setInvoice(long invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CardholderEntity getCardholder() {
        return cardholder;
    }

    public void setCardholder(CardholderEntity cardholder) {
        this.cardholder = cardholder;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    
    
}
