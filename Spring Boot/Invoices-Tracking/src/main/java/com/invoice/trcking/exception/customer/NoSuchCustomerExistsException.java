package com.invoice.trcking.exception.customer;

import org.springframework.stereotype.Component;

@Component
public class NoSuchCustomerExistsException  extends RuntimeException {
  
    private String message;
  
    public NoSuchCustomerExistsException() {}
  
    public NoSuchCustomerExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}

