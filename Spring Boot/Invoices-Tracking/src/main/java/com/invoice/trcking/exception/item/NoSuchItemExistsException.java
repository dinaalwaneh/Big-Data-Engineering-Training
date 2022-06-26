package com.invoice.trcking.exception.item;

public class NoSuchItemExistsException extends RuntimeException {
  
    private String message;
  
    public NoSuchItemExistsException() {}
  
    public NoSuchItemExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }

}
