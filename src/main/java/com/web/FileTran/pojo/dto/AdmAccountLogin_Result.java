package com.web.FileTran.pojo.dto;

import com.web.FileTran.exception.LoginException;

public class AdmAccountLogin_Result {

    public Integer Adm_Id;                    //管理员主键
    public String Adm_Name;                    //管理员昵称
    public Integer Adm_Permission;            //管理员访问权限
    public LoginException LoginException;    //登录异常

    public AdmAccountLogin_Result() {
    }

    public AdmAccountLogin_Result(int Adm_Id, String Adm_Name, int Adm_Permission) {
        this.Adm_Id = Adm_Id;
        this.Adm_Name = Adm_Name;
        this.Adm_Permission = Adm_Permission;
        this.LoginException = null;
    }

    public AdmAccountLogin_Result(LoginException exception) {
        this.Adm_Id = null;
        this.Adm_Name = null;
        this.Adm_Permission = null;
        this.LoginException = exception;
    }

    @Override
    public String toString() {
        return "AdmAccountLogin_Result{" +
                "admId=" + Adm_Id +
                ", admName='" + Adm_Name + '\'' +
                ", admPassword='" + Adm_Permission + '\'' +
                ", LoginException=" + LoginException.toString() +
                '}';
    }
}