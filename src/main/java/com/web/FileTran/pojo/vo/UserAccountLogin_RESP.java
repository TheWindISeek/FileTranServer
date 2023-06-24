package com.web.FileTran.pojo.vo;

import com.web.FileTran.pojo.dto.UserAccountLogin_Result;
import com.web.FileTran.exception.LoginException;

public class UserAccountLogin_RESP {

    public Integer User_Id;                     //普通用户的id
    public String User_Name;                    //普通用户的昵称
    public Integer User_Filelist;               //普通用户的文件列表
    public Integer User_Favorites;              //普通用户的收藏文件的id
    public LoginException LoginException;       //登录异常

    public UserAccountLogin_RESP() {
    }

    public UserAccountLogin_RESP(UserAccountLogin_Result result) {
        this.User_Id = result.User_Id;
        this.User_Name = result.User_Name;
        this.User_Filelist = result.User_Filelist;
        this.User_Favorites = result.User_Favorites;
        this.LoginException = result.LoginException;
    }

    /*正常 */
    public UserAccountLogin_RESP(Integer User_Id, String User_Name, Integer User_Filelist, Integer User_Favorites) {
        this.User_Id = User_Id;
        this.User_Name = User_Name;
        this.User_Filelist = User_Filelist;
        this.User_Favorites = User_Favorites;
        this.LoginException = null;
    }

    /*自动登录出错 */
    public UserAccountLogin_RESP(Integer User_Id, String User_Name, Integer User_Filelist, Integer User_Favorites, LoginException exception) {
        this.User_Id = User_Id;
        this.User_Name = User_Name;
        this.User_Filelist = User_Filelist;
        this.User_Favorites = User_Favorites;
        this.LoginException = exception;
    }

    @Override
    public String toString() {
        return "UserAccountLogin_RESP{" +
                "User_Id=" + User_Id +
                ", User_Name='" + User_Name + '\'' +
                ", User_Filelist='" + User_Filelist + '\'' +
                ", User_Favorites=" + User_Favorites + '\'' +
                ", LoginException=" + LoginException.toString() +
                '}';
    }
}
