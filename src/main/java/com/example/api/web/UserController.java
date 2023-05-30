package com.example.api.web;

import com.example.api.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/User")
public class UserController {

    /**
     * 用户登录
     * @param users 由传入参数自动生成
     * @param httpSession http相关
     * @param model 用于返回值
     * @return index页面
     */
    @RequestMapping("/login")
    public String login(@ModelAttribute("Users")Users users, HttpSession httpSession, Model model) {
        System.out.println("user/login");
        model.addAttribute("name", "jzh");
        model.addAttribute("password", "jzh");
        return "index";
    }


    /**
     * 用户注册
     * @param users 传入参数生成
     * @param httpSession http相关
     * @param model 返回值
     * @return index页面
     */
    @RequestMapping("/register")
    public String register(@ModelAttribute("Users")Users users, HttpSession httpSession, Model model) {
        System.out.println("user/register");
        model.addAttribute("registerFlag", true);

        return "index";
    }
}
