package com.web.FileTran.pojo.dto;

import com.web.FileTran.exception.LogoutException;

public class UserAccountLogout_Result {

    public LogoutException LogoutException;       //退出登录异常

    public UserAccountLogout_Result() {
    }

    /*退出登录出错 */
    public UserAccountLogout_Result(LogoutException exception) {
        this.LogoutException = exception;
    }

    @Override
    public String toString() {
        return "UserAccountLogout_Result{" +
                "LogoutException=" + LogoutException.toString() +
                '}';
    }
}
