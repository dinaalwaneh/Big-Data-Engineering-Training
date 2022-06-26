package com.invoice.trcking.exception.invoive;

public class NoSuchInvoiceExistsException extends RuntimeException {
  
    private String message;
  
    public NoSuchInvoiceExistsException() {}
  
    public NoSuchInvoiceExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}


