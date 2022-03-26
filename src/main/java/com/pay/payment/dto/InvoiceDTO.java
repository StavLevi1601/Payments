package com.pay.payment.dto;

import javax.validation.constraints.NotNull;

public class InvoiceDTO {

    @NotNull(message = "Invoice is required")
    private String invoice;

    
    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
       
}
