package com.web.FileTran.service.publicFun;

import javax.servlet.http.HttpSession;

public class SessionFunctions {
    public static int getUserIdFromSession(HttpSession session) {
        // TODO
        return (int) session.getAttribute("userId");
    }
}
