package com.invoice.trcking.exception.invoiceItem;

public class InvoiceItemAlreadyExistsException extends RuntimeException {
  
    private String message;
  
    public InvoiceItemAlreadyExistsException() {}
  
    public InvoiceItemAlreadyExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}