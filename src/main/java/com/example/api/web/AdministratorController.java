package com.example.api.web;

import com.example.api.pojo.Administrator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 使用model需要前端使用el表达式 现在我只想返回一个json对象
 *
 * */
@Controller
@RequestMapping("/Admin")
public class AdministratorController {

    /**
     * 管理员登录
     * @param administrator 根据传入参数生成的管理员对象
     * @return index页面
     */
    @RequestMapping("/login")
    @ResponseBody
    public Administrator login(@RequestBody Administrator administrator) {
        System.out.println("admin/login");
        System.out.println(administrator.toString());
        //这里将来会被业务逻辑取代
        administrator.setAdmPassword("lucifer");
        administrator.setAdmName("jeffrey");
        administrator.setAdmId(3);
        administrator.setAdmPermission(3);

        return administrator;
    }


    /**
     * 管理员注册
     * @param administrator 根据传入参数生成的管理员对象
     * @return 返回true or false
     */
    @RequestMapping("/register")
    @ResponseBody
    public boolean register(@RequestBody Administrator administrator) {
        System.out.println("admin/register");


        return true;
    }
}
