package com.web.FileTran.pojo.vo;

public class UserAccountLogout_REQ {

    public Integer User_Id;        // 请求的用户id

    @Override
    public String toString() {
        return "UserAccountLogout_REQ{" +
                "User_Name=" + User_Id +
                '}';
    }
}
