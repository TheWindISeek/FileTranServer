package com.web.FileTran.service;

import com.web.FileTran.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public class UserService {
    // User registration logic
    public UserVO registerUser(String username, String password, HttpSession session) {
        // TODO 实现service层
        // 首先检查session是否为已经登录的用户,若已经登录,拒绝该请求,抛出异常
        // 从dao层调用方法获取相关信息,map类型
        // 如果创建失败,抛出异常
        // 如果创建成功,从map类型的结果里取出各字段,封装成查询结果DTO
        return null;
    }

    // User login logic
    public UserVO loginUser(String username, String password, HttpSession session) {
        // TODO 实现service层
        // 首先检查session是否为已经登录的用户,若已经登录,拒绝该请求,抛出异常
        // 然后调用dao层的userLogin方法,返回一个map,如果查询不到任何结果,抛出异常
        // 比较用户密码和输入是否一致,输入不一致抛出异常
        // 输入一致,封装VO作为返回
        return null;
    }

    // User logout logic
    public boolean logoutUser(HttpSession session) {
        // TODO 实现service层
        // 检查用户的session,如果未登录,抛出异常,如果登录,使session失效,清除id到session的映射
        // Invalidate session
        session.invalidate();
        return true;
    }

    // Get user information by ID
    public UserVO getUserInfoById(long userId,HttpSession session) {
        // TODO 实现service层
        // Set the properties of the user object based on the retrieved information
        // 检查用户的session,如果未登录,抛出异常
        // 调用dao层的getUserInfoById方法,得到
        // 把用户的公开信息封装成VO
        return null;
    }
}