package com.web.FileTran.pojo.vo;

public class UserAccountDereg_REQ {

    public String User_Name;        // 请求的用户id
    public String User_Password;    // 请求的用户密码

    @Override
    public String toString() {
        return "UserAccountDereg_REQ{" +
                "User_Name=" + User_Name +
                ", User_Password='" + User_Password + '\'' +
                '}';
    }
}
