package com.web.FileTran.exception;

public class RegisterException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1284038920521831302L;
    private String User_Name;
    private String User_Password;
    private String Exception_Type;

    public RegisterException(String User_Name, String User_Password, String Exception_Type) {
        this.User_Name = User_Name;
        this.User_Password = User_Password;
        this.Exception_Type = Exception_Type;
    }

    @Override
    public String toString() {
        return "RegisterException:{" + "User_Name=" + User_Name + ", User_Password" + User_Password + ", Exception_Type" + Exception_Type + "}";
    }
}
