package com.pay.payment.respository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.pay.payment.dto.CreatePaymentDTO;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentsRepository {

    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

    public void savePayment(CreatePaymentDTO reqBody) {
        new DynamoDBMapper(client).save(reqBody);
    }

}
