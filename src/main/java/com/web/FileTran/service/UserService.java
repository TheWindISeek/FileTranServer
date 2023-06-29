package com.web.FileTran.service;

import com.web.FileTran.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public interface UserService {
    /**
     * User registration logic
     * @param username 用户名
     * @param password 密码
     * @param session 会话
     * @return 用户 信息传输类
     */
    UserDTO registerUser(String username, String password, HttpSession session);

    /**
     * User login logic
     * @param username 用户名
     * @param password 密码
     * @param session 会话
     * @return 用户 信息传输类
     */
    UserDTO loginUser(String username, String password, HttpSession session);

    /**
     * User logout logic
     * @param session 会话
     * @return 操作结果,成功退出登录为真,其他为假
     */
    boolean logoutUser(HttpSession session);

    /**
     * Get user information by ID
     * @param userId 用户名
     * @param session 会话
     * @return 用户 信息传输类
     */
    UserDTO getUserInfoById(long userId, HttpSession session);
}