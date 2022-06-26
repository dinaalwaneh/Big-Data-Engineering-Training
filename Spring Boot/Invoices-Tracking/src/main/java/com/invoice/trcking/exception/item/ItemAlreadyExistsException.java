package com.invoice.trcking.exception.item;

public class ItemAlreadyExistsException extends RuntimeException {
  
    private String message;
  
    public ItemAlreadyExistsException() {}
  
    public ItemAlreadyExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }

}
