package com.web.FileTran.web.function;

import com.web.FileTran.pojo.po.Users;
import com.web.FileTran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param users 由传入参数自动生成
     * @return 是否登录成功
     */
    @RequestMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody Users users) {
        System.out.println("user/login");

        return userService.login(users);
    }


    /**
     * 用户注册
     *
     * @param users 传入参数生成
     * @return index页面
     */
    @RequestMapping("/register")
    @ResponseBody
    public int register(@RequestBody Users users) {
        System.out.println("user/register");


        return userService.register(users);
    }
}
