package com.invoice.trcking.exception.invoive;

public class InvoiceAlreadyExistsException extends RuntimeException {
  
    private String message;
  
    public InvoiceAlreadyExistsException() {}
  
    public InvoiceAlreadyExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
