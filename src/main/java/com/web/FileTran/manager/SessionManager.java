package com.web.FileTran.manager;

import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SessionManager {

    //使用HashMap代替Redis实现键值对的存储,Redis学习成本太高后面再看
    private static Map<String, Long> SessionId_UserMap = new HashMap<>();
    private static Map<Long, String> User_SessionIdMap = new HashMap<>();

    /**
     * 给session设置登录信息,建立双向映射
     * @param session 会话
     * @param userid 用户id
     */
    public static void setLoginInfo(HttpSession session, Long userid)
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
    public static void setLoginInfo(String sessionId, Long userid)
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
        long userid = SessionId_UserMap.get(sessionId);
        User_SessionIdMap.remove(userid);
        SessionId_UserMap.remove(sessionId);
    }

    /**
     * 把session的登录信息移除
     * @param sessionId 会话id
     */
    public static void removeLoginInfo(String sessionId) {
        // 移除sessionId和UserID的双向映射
        long userid = SessionId_UserMap.get(sessionId);
        User_SessionIdMap.remove(userid);
        SessionId_UserMap.remove(sessionId);
    }

    public static void resetExpire(HttpSession session)
    {
        // TODO 重置过期时间(似乎不需要了)
    }

    public static Long getUserIdBySessionId(String sessionId) {
        // 通过session得到用户id
        Long userid = SessionId_UserMap.get(sessionId);
        return userid;
    }

    public static String getSessionIdByUserId(long userId) {
        // 通过id得到正在登录的此账号的sessionId
        String sessionId = User_SessionIdMap.get(userId);
        return sessionId;
    }

    public static boolean LoginInfoCheck(HttpSession session)
    {
        // TODO 检查登录是否有效(似乎不需要了)
        return false;
    }
}
