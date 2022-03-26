package com.pay.payment.controller;

import javax.validation.Valid;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.pay.payment.dto.InvoiceDTO;
import com.pay.payment.service.MapValidationErrorService;
import com.pay.payment.service.RetrieveTransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/retrieveTransaction")
public class RetrieveTransactionController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @Autowired
    private RetrieveTransactionService retrieveTransactionService;

    @PostMapping
    @RequestMapping("/retrieveTransaction")
    public ResponseEntity<?> retrieveTransaction(@RequestBody @Valid InvoiceDTO reqBody, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationRetrieveTransaction(result,reqBody);
        if (errorMap!=null)
        {
            return errorMap;
        }
        ResponseEntity<?> response = retrieveTransactionService.OutputRetrieveTransaction(result,reqBody);
        return response; 
    }
}
