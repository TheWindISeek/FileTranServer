package com.web.FileTran.pojo.dto;

import com.web.FileTran.exception.DeregException;

public class UserAccountDereg_Result {

    public DeregException DeregException;       //注销异常

    public UserAccountDereg_Result() {
        this.DeregException = null;
    }

    /*注销出错 */
    public UserAccountDereg_Result(DeregException exception) {
        this.DeregException = exception;
    }

    @Override
    public String toString() {
        return "UserAccountDereg_Result{" +
                "DeregException=" + DeregException.toString() +
                '}';
    }
}
