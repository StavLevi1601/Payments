package com.pay.payment.dto;

import javax.validation.constraints.NotNull;

public class InvoiceDTO {

    @NotNull(message = "Invoice is required")
    private long invoice;

    
    public long getInvoice() {
        return invoice;
    }

    public void setInvoice(long invoice) {
        this.invoice = invoice;
    }
       
}
