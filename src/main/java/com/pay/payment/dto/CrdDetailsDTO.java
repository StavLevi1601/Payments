package com.pay.payment.dto;

import javax.validation.constraints.NotBlank;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class CrdDetailsDTO {

    @NotBlank(message = "cardPan is required")
    private String pan;

    @NotBlank(message = "expiry is required")
    private String expiry;

    @NotBlank(message = "CVV is required")
    private String cvv;

    @DynamoDBAttribute(attributeName = "Pan")
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @DynamoDBAttribute(attributeName = "Expiry")
    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    @DynamoDBAttribute(attributeName = "Cvv")
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    
    
}
