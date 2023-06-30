package com.web.FileTran.listener;

import com.web.FileTran.manager.SessionManager;
import com.web.FileTran.manager.SessionStorage;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        // 将 SessionID 存入 SessionManager
        SessionStorage.addSession(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        // Session 销毁前应使账号退出登录
        SessionManager.removeLoginInfo(sessionId);
        // Session 销毁时从 SessionManager 中删除对应的键值对
        SessionStorage.removeSession(sessionId);
    }
}
