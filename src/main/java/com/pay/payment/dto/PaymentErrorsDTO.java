package com.pay.payment.dto;

public class PaymentErrorsDTO {

    
     private String currency;
     private String invoice;
     private String amount;
     private String cardHolderName;
     private String cardHolderEmail;
     private String cardPan;
     private String cardExpiry;
     private String cardCvv;
     
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getInvoice() {
        return invoice;
    }
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public String getCardHolderEmail() {
        return cardHolderEmail;
    }
    public void setCardHolderEmail(String cardHolderEmail) {
        this.cardHolderEmail = cardHolderEmail;
    }
    public String getCardPan() {
        return cardPan;
    }
    public void setCardPan(String cardPan) {
        this.cardPan = cardPan;
    }
    public String getCardExpiry() {
        return cardExpiry;
    }
    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }
    public String getCardCvv() {
        return cardCvv;
    }
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }


    

     

    
}
