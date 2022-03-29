package com.pay.payment.controller;

import java.text.ParseException;

import javax.validation.Valid;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.pay.payment.dto.CreatePaymentDTO;
import com.pay.payment.service.CreatePaymentService;
import com.pay.payment.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @Autowired
    private CreatePaymentService createPaymentService;
    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

    
    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody @Valid CreatePaymentDTO reqBody, BindingResult result) throws ParseException
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationCreatePayment(result);

        if (errorMap != null)
        {
            return errorMap;
        }
        errorMap = mapValidationErrorService.mapValidateExpiryDate(reqBody);
        if  (errorMap!=null)
        {
            return errorMap;
        }
        ResponseEntity<?> response = createPaymentService.OutputCreatePayment(result);
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(reqBody);
        return response;
    }
}
