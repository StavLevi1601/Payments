package com.pay.payment.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class CardHolderDTO {

    @NotBlank(message = "cardholderName is required")
    private String name;

    @NotBlank(message = "cardholderEmail is required")
    @Email(message = "Email should be valid")
    private String email;

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
