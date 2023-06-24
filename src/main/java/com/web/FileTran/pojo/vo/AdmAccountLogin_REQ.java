package com.web.FileTran.pojo.vo;

public class AdmAccountLogin_REQ {

    public String Adm_Name;        //管理员主键
    public String Adm_Password;    //管理员密码

    @Override
    public String toString() {
        return "Administrator{" +
                "Adm_Name=" + Adm_Name +
                ", Adm_Password='" + Adm_Password + '\'' +
                '}';
    }
}