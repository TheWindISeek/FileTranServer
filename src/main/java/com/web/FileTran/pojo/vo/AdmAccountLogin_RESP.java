package com.web.FileTran.pojo.vo;

import com.web.FileTran.pojo.dto.AdmAccountLogin_Result;
import com.web.FileTran.exception.LoginException;

public class AdmAccountLogin_RESP {

    public Integer Adm_Id;                    //管理员主键
    public String Adm_Name;                    //管理员昵称
    public Integer Adm_Permission;            //管理员访问权限
    public LoginException LoginException;    //登录异常

    public AdmAccountLogin_RESP() {
    }

    public AdmAccountLogin_RESP(AdmAccountLogin_Result result) {
        this.Adm_Id = result.Adm_Id;
        this.Adm_Name = result.Adm_Name;
        this.Adm_Permission = result.Adm_Permission;
        this.LoginException = result.LoginException;
    }

    public AdmAccountLogin_RESP(int Adm_Id, String Adm_Name, int Adm_Permission) {
        this.Adm_Id = Adm_Id;
        this.Adm_Name = Adm_Name;
        this.Adm_Permission = Adm_Permission;
        this.LoginException = null;
    }

    public AdmAccountLogin_RESP(LoginException exception) {
        this.Adm_Id = null;
        this.Adm_Name = null;
        this.Adm_Permission = null;
        this.LoginException = exception;
    }

    @Override
    public String toString() {
        return "AdmAccountLogin_RESP{" +
                "admId=" + Adm_Id +
                ", admName='" + Adm_Name + '\'' +
                ", admPassword='" + Adm_Permission + '\'' +
                ", LoginException=" + LoginException.toString() +
                '}';
    }
}