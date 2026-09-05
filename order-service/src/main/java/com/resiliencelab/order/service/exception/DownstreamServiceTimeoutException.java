package com.resiliencelab.order.service.exception;

public class DownstreamServiceTimeoutException  extends RuntimeException{

    public DownstreamServiceTimeoutException(String message,Throwable cause){
        super(message,cause);
    }
}
