package com.pay.payment.controller;

import javax.validation.Valid;

import com.pay.payment.dto.InvoiceDTO;
import com.pay.payment.respository.RetrieveTransactionRepository;
import com.pay.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/retrieveTransaction")
public class RetrieveTransactionController {

    @Autowired
    private PaymentService mapValidationErrorService;
    @Autowired
    private RetrieveTransactionRepository retrieveTransactionRepository;

    @PostMapping
    @RequestMapping("/retrieveTransaction")
    public ResponseEntity<?> retrieveTransaction(@RequestBody @Valid InvoiceDTO reqBody) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationRetrieveTransaction(reqBody);
        return null != errorMap? errorMap: retrieveTransactionRepository.outputRetrieveTransaction(reqBody);
    }
}
