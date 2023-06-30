package com.web.FileTran.manager;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionStorage {

    private static final Map<String, HttpSession> sessionMap = new HashMap<>();

    /**
     * 建立键值对
     * @param sessionId Id
     * @param session 新session
     */
    public static void addSession(String sessionId, HttpSession session) {
        sessionMap.put(sessionId, session);
    }

    /**
     * 通过键值对得到session
     * @param sessionId id
     * @return session
     */
    public static HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }


    /**
     * 删除指定的sessionId
     * @param sessionId id
     */
    public static void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }
}
