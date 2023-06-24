package com.web.FileTran.exception;

public class DeregException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 5781745582685052917L;
    private String User_Name;
    private String User_Password;
    private String Exception_Type;

    public DeregException(String User_Name, String User_Password, String Exception_Type) {
        this.User_Name = User_Name;
        this.User_Password = User_Password;
        this.Exception_Type = Exception_Type;
    }

    @Override
    public String toString() {
        return "DeregException:{" + "User_Name=" + User_Name + ", User_Password" + User_Password + ", Exception_Type" + Exception_Type + "}";
    }
}
