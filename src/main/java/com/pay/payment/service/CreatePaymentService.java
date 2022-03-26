package com.pay.payment.service;

import com.pay.payment.dto.CreatePaymentResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CreatePaymentService {

    public ResponseEntity<?> OutputCreatePayment(BindingResult result)
    {
        CreatePaymentResponse createPaymentResponseDTO = new CreatePaymentResponse();
        createPaymentResponseDTO.setApproved(true);
        return new ResponseEntity<>(createPaymentResponseDTO, HttpStatus.ACCEPTED);
    }
    
}
