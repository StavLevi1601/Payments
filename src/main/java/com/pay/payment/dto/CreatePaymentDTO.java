package com.pay.payment.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "Payments")
public class CreatePaymentDTO {

    @NotBlank(message = "Currency is required")
     private String currency;

    @NotNull(message = "invoice is required")
    // Partition key
    @DynamoDBHashKey(attributeName = "invoice")
    private String invoice;

     @Min(1)
     @NotNull
     private BigDecimal amount;

    @Valid
    private CardHolderDTO cardHolder;
    @Valid
    private CrdDetailsDTO card;
    
    @DynamoDBAttribute(attributeName = "Currency")
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

    @DynamoDBAttribute(attributeName = "Amount")
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @DynamoDBAttribute(attributeName = "CardHolder")
    public CardHolderDTO getCardHolder() {
        return cardHolder;
    }
    public void setCardHolder(CardHolderDTO cardHolder) {
        this.cardHolder = cardHolder;
    }

    @DynamoDBAttribute(attributeName = "Card")
    public CrdDetailsDTO getCard() {
        return card;
        
    }
    public void setCard(CrdDetailsDTO card) {
        this.card = card;
    }

    

     

    
}
