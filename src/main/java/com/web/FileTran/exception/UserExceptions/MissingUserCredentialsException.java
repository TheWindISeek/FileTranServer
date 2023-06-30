package com.web.FileTran.exception.UserExceptions;

public class MissingUserCredentialsException extends Exception {
    // 用户不存在异常
    public MissingUserCredentialsException(String message) {
        super(message);
    }
}