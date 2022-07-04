package com.invoice.trcking.exception.invoice.item;

public class NoSuchInvoiceItemExistsException extends RuntimeException {
  
    private String message;
  
    public NoSuchInvoiceItemExistsException() {}
  
    public NoSuchInvoiceItemExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}

