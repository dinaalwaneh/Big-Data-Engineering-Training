package com.invoice.trcking.exception;

public class EmptyValueException extends RuntimeException {
	  
    private String message;
  
    public EmptyValueException() {}
  
    public EmptyValueException(String msg)
    {
        super(msg);
        this.message = msg;
    }
    
}
