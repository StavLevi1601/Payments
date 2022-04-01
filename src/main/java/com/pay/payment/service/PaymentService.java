package com.pay.payment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.pay.payment.dto.*;

import com.pay.payment.exception.PaymentsException;
import com.pay.payment.respository.PaymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentsRepository paymentsRepository;

    public ResponseEntity<?> createPayment(CreatePaymentDTO reqBody, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationCreatePayment(result);
        if (null != errorMap) {
            return errorMap;
        }
        try {
            errorMap = mapValidateExpiryDate(reqBody);
        } catch (ParseException e) {
            log.error("Exception occurred while parsing error map");
            throw new PaymentsException("Exception occurred while parsing error map");
        }
        if (null != errorMap) {
            return errorMap;
        }
        paymentsRepository.savePayment(reqBody);
        return new ResponseEntity<>(new CreatePaymentResponse(true), HttpStatus.ACCEPTED);
    }
    

    public ResponseEntity<?> mapValidationService(BindingResult result){
        //checkif has error
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            //put error on errorMap
            if(null != result.getFieldErrors()) {
                for (FieldError error: result.getFieldErrors()) {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }


    public ResponseEntity<?> mapValidationCreatePayment(BindingResult result){
       //checkif has error
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            //put error on errorMap
            if(null != result.getFieldErrors()) {
                for (FieldError error : result.getFieldErrors()) {
                    String fieldError = error.getField().replaceAll("[.]", "");
                    log.info(fieldError);
                    if (null != error.getDefaultMessage()) {
                        errorMap.put(fieldError, error.getDefaultMessage());
                    }
                }
            }
            PaymentErrorsDTO paymentErrorsDTO = modelMapper.map(errorMap, PaymentErrorsDTO.class) ;
            return new ResponseEntity<>(new CreatePaymentResponseDTO(false, paymentErrorsDTO),
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<?> mapValidationRetrieveTransaction(@Valid InvoiceDTO reqBody) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        CreatePaymentDTO itemRetrieved = mapper.load(CreatePaymentDTO.class, reqBody.getInvoice());
        return (null == itemRetrieved)? new ResponseEntity<>
                ("Invoice is not existing", HttpStatus.BAD_REQUEST): null;
    }

    public ResponseEntity<?> mapValidateExpiryDate(@Valid CreatePaymentDTO reqBody) throws ParseException 
    {
        CreatePaymentResponseDTO createPaymentResponseDTO = new CreatePaymentResponseDTO();
        Map<String, String> errorMapping = new HashMap<>();
        String expiry = reqBody.getCard().getExpiry();
        String twoFirstNumber= expiry.substring(0, 2);
        String twoLastNumber= expiry.substring(2, expiry.length());
        LocalDateTime now = LocalDateTime.now();  
        String stringDate = now.toString();
        String dateYearString = stringDate.substring(2, 4);
        String dateMonthString = stringDate.substring(5, 7);
        String fullDate = dateMonthString+dateYearString;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String twoLastNumberYear = String.valueOf(currentYear).substring(2, 4);
        if ((Integer.parseInt(twoFirstNumber)<1) || (Integer.parseInt(twoFirstNumber)>12))
        {
            if (Integer.parseInt(twoLastNumber)<Integer.parseInt(twoLastNumberYear))
            {
                createPaymentResponseDTO.setApproved(false);
                errorMapping.put("cardExpiry", "the expiry date is not valid");
                PaymentErrorsDTO paymentErrorsDTO = new PaymentErrorsDTO();
                paymentErrorsDTO.setCardExpiry(errorMapping.get("cardExpiry"));
                createPaymentResponseDTO.setErrors(paymentErrorsDTO);
                return new ResponseEntity<>(createPaymentResponseDTO, HttpStatus.BAD_REQUEST);
            }          
        }
        Date dateExpiry=new SimpleDateFormat("MMyy").parse(twoFirstNumber+twoLastNumber);
        Date LocalDate = new SimpleDateFormat("MMyy").parse(fullDate);
        boolean isBefore = dateExpiry.before(LocalDate);
        if (isBefore)
        {
            createPaymentResponseDTO.setApproved(false);
            errorMapping.put("cardExpiry", "the expiry date cant be before the local date");
            PaymentErrorsDTO paymentErrorsDTO = new PaymentErrorsDTO();
            paymentErrorsDTO.setCardExpiry(errorMapping.get("cardExpiry"));
            createPaymentResponseDTO.setErrors(paymentErrorsDTO);
            return new ResponseEntity<>(createPaymentResponseDTO, HttpStatus.BAD_REQUEST);

        }
        return null;  
    }
    
}
