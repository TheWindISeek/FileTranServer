package com.web.FileTran.service;

import com.web.FileTran.pojo.dto.AdmAccountLogin_Result;
import com.web.FileTran.pojo.po.Administrator;

import javax.servlet.http.HttpSession;


/**
 * @author JeffreySharp
 * @apiNote 为administrator controller 提供服务
 */
public interface AdministratorService {
    /**
     * 给定对象进行登录
     *
     * @param administrator 给定的管理员对象
     * @return 登录成功返回true
     */
    boolean login(Administrator administrator);

    /**
     * 给定对象进行注册
     *
     * @param administrator 给定的管理员对象
     * @return 注册成功返回true
     */
    int register(Administrator administrator);

//    /**
//     * 新.给定对象进行登录
//     *
//     * @param administrator 给定的管理员对象
//     * @return 登录成功返回true
//     */
//    AdmAccountLogin_Result AdmAccountLogin(HttpSession session, String Adm_Name, String Adm_Password);
}
