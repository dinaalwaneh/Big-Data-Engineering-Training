package com.invoice.trcking.exception;

public class NullValueException extends RuntimeException {
	  
    private String message;
  
    public NullValueException() {}
  
    public NullValueException(String msg)
    {
        super(msg);
        this.message = msg;
    }
    
}