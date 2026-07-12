package com.remindly.backend.exception;

public class dInvalidCredentialsException extends  RuntimeException{

    public dInvalidCredentialsException (String message) {
        super(message);
    }
}
