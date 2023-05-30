package com.example.api.web;

import com.example.api.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {

    /**
     * 用户登录
     * @param users 由传入参数自动生成
     * @return index页面
     */
    @RequestMapping("/login")
    @ResponseBody
    public Users login(@RequestBody Users users) {
        System.out.println("user/login");
        users.setUserId(1);
        users.setUserName("jzh");

        return users;
    }


    /**
     * 用户注册
     * @param users 传入参数生成
     * @return index页面
     */
    @RequestMapping("/register")
    @ResponseBody
    public boolean register(@RequestBody Users users) {
        System.out.println("user/register");


        return true;
    }
}
