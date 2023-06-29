package com.web.FileTran.newexception.UserExceptions;

public class MissingUserCredentialsException extends Exception {
    public MissingUserCredentialsException(String message) {
        super(message);
    }
}