package com.web.FileTran.manager;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionManager {

    //使用HashMap代替Redis实现键值对的存储,Redis学习成本太高后面再看
    private static final Map<String, Integer> SessionId_UserMap = new HashMap<>();
    private static final Map<Integer, String> User_SessionIdMap = new HashMap<>();

    /**
     * 给session设置登录信息,建立双向映射
     * @param session 会话
     * @param userid 用户id
     */
    public static void setLoginInfo(HttpSession session, int userid)
    {
        // 只负责给session添加id密码并建立双向的映射
        // 登录信息存储,生成sessionId和userid的双向映射
        String sessionId = session.getId();
        SessionId_UserMap.put(sessionId,userid);
        User_SessionIdMap.put(userid,sessionId);
    }

    /**
     * 给session设置登录信息,建立双向映射
     * @param sessionId 会话id
     * @param userid 用户id
     */
    public static void setLoginInfo(String sessionId, int userid)
    {
        // 只负责给session添加id密码并建立双向的映射
        // 登录信息存储,生成sessionId和userid的双向映射
        SessionId_UserMap.put(sessionId,userid);
        User_SessionIdMap.put(userid,sessionId);
    }

    /**
     * 把session的登录信息移除
     * @param session 会话
     */
    public static void removeLoginInfo(HttpSession session) {
        // 移除sessionId和UserID的双向映射
        String sessionId = session.getId();
        int userid = SessionId_UserMap.get(sessionId);
        User_SessionIdMap.remove(userid);
        SessionId_UserMap.remove(sessionId);
    }

    /**
     * 把session的登录信息移除
     * @param sessionId 会话id
     */
    public static void removeLoginInfo(String sessionId) {
        // 移除sessionId和UserID的双向映射
        int userid = SessionId_UserMap.get(sessionId);
        User_SessionIdMap.remove(userid);
        SessionId_UserMap.remove(sessionId);
    }

    public static void resetExpire(HttpSession session)
    {
        // TODO 重置过期时间(似乎不需要了)
    }

    public static Integer getUserIdBySessionId(String sessionId) {
        // 通过session得到用户id
        return SessionId_UserMap.get(sessionId);
    }

    public static String getSessionIdByUserId(int userId) {
        // 通过id得到正在登录的此账号的sessionId
        return User_SessionIdMap.get(userId);
    }

    public static boolean LoginInfoCheck(HttpSession session)
    {
        // TODO 检查登录是否有效(似乎不需要了)
        return false;
    }
}
