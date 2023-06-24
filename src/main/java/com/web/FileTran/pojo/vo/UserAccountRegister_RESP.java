package com.web.FileTran.pojo.vo;

import com.web.FileTran.pojo.dto.UserAccountRegister_Result;
import com.web.FileTran.exception.LoginException;
import com.web.FileTran.exception.RegisterException;

public class UserAccountRegister_RESP {

    public Integer User_Id;                     //普通用户的id
    public String User_Name;                    //普通用户的昵称
    public Integer User_Filelist;               //普通用户的文件列表
    public Integer User_Favorites;              //普通用户的收藏文件的id
    public RegisterException RegisterException; //注册异常
    public LoginException LoginException;       //自动登录异常

    public UserAccountRegister_RESP() {
    }

    public UserAccountRegister_RESP(UserAccountRegister_Result result) {
        this.User_Id = result.User_Id;
        this.User_Name = result.User_Name;
        this.User_Filelist = result.User_Filelist;
        this.User_Favorites = result.User_Favorites;
        this.RegisterException = result.RegisterException;
        this.LoginException = result.LoginException;
    }

    /*正常 */
    public UserAccountRegister_RESP(Integer User_Id, String User_Name, Integer User_Filelist, Integer User_Favorites) {
        this.User_Id = User_Id;
        this.User_Name = User_Name;
        this.User_Filelist = User_Filelist;
        this.User_Favorites = User_Favorites;
        this.RegisterException = null;
        this.LoginException = null;
    }

    /*注册出错 */
    public UserAccountRegister_RESP(RegisterException exception) {
        this.User_Id = null;
        this.User_Name = null;
        this.User_Filelist = null;
        this.User_Favorites = null;
        this.RegisterException = exception;
        this.LoginException = null;
    }

    /*自动登录出错 */
    public UserAccountRegister_RESP(Integer User_Id, String User_Name, Integer User_Filelist, Integer User_Favorites, LoginException exception) {
        this.User_Id = User_Id;
        this.User_Name = User_Name;
        this.User_Filelist = User_Filelist;
        this.User_Favorites = User_Favorites;
        this.RegisterException = null;
        this.LoginException = exception;
    }

    @Override
    public String toString() {
        return "UserAccountRegister_RESP{" +
                "User_Id=" + User_Id +
                ", User_Name='" + User_Name + '\'' +
                ", User_Filelist='" + User_Filelist + '\'' +
                ", User_Favorites=" + User_Favorites + '\'' +
                ", RegisterException=" + RegisterException.toString() +
                ", LoginException=" + LoginException.toString() +
                '}';
    }
}