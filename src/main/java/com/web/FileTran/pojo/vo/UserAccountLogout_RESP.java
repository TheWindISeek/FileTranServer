package com.web.FileTran.pojo.vo;

import com.web.FileTran.pojo.dto.UserAccountLogout_Result;
import com.web.FileTran.exception.LogoutException;

public class UserAccountLogout_RESP {

    public LogoutException LogoutException;       //注销异常

    public UserAccountLogout_RESP() {
    }

    public UserAccountLogout_RESP(UserAccountLogout_Result result) {
        this.LogoutException = result.LogoutException;
    }

    /*退出登录出错 */
    public UserAccountLogout_RESP(LogoutException exception) {
        this.LogoutException = exception;
    }

    @Override
    public String toString() {
        return "UserAccountLogout_RESP{" +
                "LogoutException=" + LogoutException.toString() +
                '}';
    }
}
