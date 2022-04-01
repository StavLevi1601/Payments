package com.pay.payment.controller;

import javax.validation.Valid;

import com.pay.payment.dto.CreatePaymentDTO;
import com.pay.payment.service.PaymentService;
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
    private PaymentService paymentService;

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody @Valid CreatePaymentDTO reqBody, BindingResult result) {
        return paymentService.createPayment(reqBody, result);
    }
}
