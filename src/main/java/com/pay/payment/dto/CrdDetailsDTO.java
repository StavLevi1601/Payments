package com.pay.payment.dto;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import org.springframework.format.annotation.DateTimeFormat;

@DynamoDBDocument
public class CrdDetailsDTO {

    @NotBlank(message = "cardPan is required")
    @Pattern(regexp="[\\d]{16}", message="This field should contain 16 digits!")
    private String pan;

    //@CheckDateFormat(pattern = "MMyy")
    @NotBlank(message = "CVV is required")
    //@FutureOrPresent
    @Size(min = 4,max = 4)
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
