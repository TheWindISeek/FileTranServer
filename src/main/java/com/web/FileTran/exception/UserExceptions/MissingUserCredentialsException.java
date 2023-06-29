package com.web.FileTran.exception.UserExceptions;

public class MissingUserCredentialsException extends Exception {
    public MissingUserCredentialsException(String message) {
        super(message);
    }
}