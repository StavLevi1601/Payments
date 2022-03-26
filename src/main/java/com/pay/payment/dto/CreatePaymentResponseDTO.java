package com.pay.payment.dto;

public class CreatePaymentResponseDTO {

    private Boolean approved;
    private PaymentErrorsDTO errors;

    public CreatePaymentResponseDTO() {}

    public Boolean getApproved() {
        return approved;
    }
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
    public PaymentErrorsDTO getErrors() {
        return errors;
    }
    public void setErrors(PaymentErrorsDTO errors) {
        this.errors = errors;
    }
}
