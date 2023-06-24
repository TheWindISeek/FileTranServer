package com.web.FileTran.exception;

public class LogoutException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -4628324038742972120L;
    private Integer User_Id;
    private String Exception_Type;

    public LogoutException(Integer User_Id, String Exception_Type) {
        this.User_Id = User_Id;
        this.Exception_Type = Exception_Type;
    }

    @Override
    public String toString() {
        return "LogoutException:{" + "User_Id=" + User_Id + ", Exception_Type" + Exception_Type + "}";
    }
}
