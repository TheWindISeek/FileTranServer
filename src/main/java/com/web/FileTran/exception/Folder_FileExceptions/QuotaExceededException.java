package com.web.FileTran.exception.Folder_FileExceptions;

public class QuotaExceededException extends Exception {
    public QuotaExceededException(String message) {
        super(message);
    }
}