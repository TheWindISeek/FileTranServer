package com.web.FileTran.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Component
public class SessionManager {
    private static final String SESSION_KEY_PREFIX = "user-session:";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static void setLoginInfo(HttpSession session, Long userid, String password)
    {
        // TODO 只负责给session添加id密码并建立双向的映射
        // TODO 学习在redis上建立映射的方法,实在不行用map也可以临时代替一下
    }
    public static int getUserIdFromSession(HttpSession session) {
        // TODO 完成方法
        return (int) session.getAttribute("userId");
    }

    public static boolean LoginInfoCheck(HttpSession session)
    {
        // TODO 检查登录是否有效
        return false;
    }

    public void setSessionId(int userId, String sessionId) {
        String key = getSessionKey(userId);
        redisTemplate.opsForValue().set(key, sessionId);
        redisTemplate.expire(key, 30, TimeUnit.MINUTES); // 设置过期时间为30分钟
    }

    public String getSessionId(int userId) {
        String key = getSessionKey(userId);
        return redisTemplate.opsForValue().get(key);
    }

    public void removeSessionId(int userId) {
        String key = getSessionKey(userId);
        redisTemplate.delete(key);
    }

    private String getSessionKey(int userId) {
        return SESSION_KEY_PREFIX + userId;
    }
}
