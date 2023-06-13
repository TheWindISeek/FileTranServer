package com.web.FileTran.service;

import com.web.FileTran.pojo.dto.UserAccountLogin_Result;
import com.web.FileTran.pojo.dto.UserAccountRegister_Result;
import com.web.FileTran.pojo.po.Users;

import javax.servlet.http.HttpSession;

/**
 * @author JeffreySharp
 * @apiNote 为User Controller层提供服务
 */
public interface UserService {
    /**
     * 用户据是否成功登录
     *
     * @param users pojo
     * @return 成功则为true 失败为false
     */
    boolean login(Users users);

    /**
     * 用户是否注册成功
     *
     * @param users pojo
     * @return 成功则为true 失败则为false
     */
    int register(Users users);

//    /**
//     * 新.用户据是否成功登录
//     *
//     * @param users
//     * @return 成功则为true 失败为false
//     */
//    UserAccountLogin_Result UserAccountLogin(HttpSession session, String User_Name, String User_Password);
//
//    /**
//     * 新.用户据是否成功注册
//     *
//     * @param users
//     * @return 成功则为true 失败为false
//     */
//    UserAccountRegister_Result UserAccountRegister(HttpSession session, String User_Name, String User_Password);
}
