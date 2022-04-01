package com.pay.payment.respository;

import javax.validation.Valid;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.pay.payment.dto.CreatePaymentDTO;
import com.pay.payment.dto.InvoiceDTO;
import com.pay.payment.dto.RetrieveTransactionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RetrieveTransactionRepository {

    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

    public ResponseEntity<?> outputRetrieveTransaction(@Valid InvoiceDTO reqBody)
    {
        RetrieveTransactionResponseDTO retrieveTransactionResponseDTO = new RetrieveTransactionResponseDTO();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        CreatePaymentDTO itemRetrieved = mapper.load(CreatePaymentDTO.class, reqBody.getInvoice());
        retrieveTransactionResponseDTO.setCardHolderName(retrieveTransactionResponseDTO.
                encryptionNameAndExpiryDate(itemRetrieved.getCardHolder().getName()));
        retrieveTransactionResponseDTO.setPan(retrieveTransactionResponseDTO.
                encryptPan(itemRetrieved.getCard().getPan()));
        retrieveTransactionResponseDTO.setExpiryDate(retrieveTransactionResponseDTO.
                encryptionNameAndExpiryDate(itemRetrieved.getCard().getExpiry()));
        return new ResponseEntity<>(retrieveTransactionResponseDTO,org.springframework.http.HttpStatus.ACCEPTED);
    }

}
