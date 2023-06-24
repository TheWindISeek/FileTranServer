package com.web.FileTran.pojo.vo;

import com.web.FileTran.pojo.dto.UserAccountDereg_Result;
import com.web.FileTran.exception.DeregException;

public class UserAccountDereg_RESP {

    public DeregException DeregException;       //注销异常

    public UserAccountDereg_RESP() {
    }

    public UserAccountDereg_RESP(UserAccountDereg_Result result) {
        this.DeregException = result.DeregException;
    }

    /*注销出错 */
    public UserAccountDereg_RESP(DeregException exception) {
        this.DeregException = exception;
    }

    @Override
    public String toString() {
        return "UserAccountDereg_RESP{" +
                "DeregException=" + DeregException.toString() +
                '}';
    }
}
