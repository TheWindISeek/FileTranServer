package com.example.api.service;

import com.example.api.pojo.Users;

/**
 * @author JeffreySharp
 * @apiNote 为User Controller层提供服务
 */
public interface UserService {
    /**
     * 用户据是否成功登录
     * @param users
     * @return 成功则为true 失败为false
     */
    boolean login(Users users);

    /**
     * 用户是否注册成功
     * @param users
     * @return 成功则为true 失败则为false
     */
    int register(Users users);
}
