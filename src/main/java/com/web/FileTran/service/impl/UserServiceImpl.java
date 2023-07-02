package com.web.FileTran.service.impl;

import com.web.FileTran.dao.usersMapper;
import com.web.FileTran.dto.UserDTO;
import com.web.FileTran.exception.UserExceptions.IncorrectPasswordException;
import com.web.FileTran.manager.SessionManager;
import com.web.FileTran.pojo.users;
import com.web.FileTran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private usersMapper usersMapper;
    // User registration logic
    //@Transactional
    @Override
    public UserDTO registerUser(String username, String password, HttpSession session) {
        // TODO 实现service层
        // 首先检查session是否为已经登录的用户,若已经登录,拒绝该请求,抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId != null)
        {
            throw new IllegalStateException("已经登录,需退出登录再注册");
        }
        // 从dao层调用方法获取相关信息,map类型
        Map<String, Object> params = new HashMap<>();
        params.put("userName", username);
        params.put("userPassword", password);
        usersMapper.registerUser(params);
        Integer newUserId = (Integer) params.get("userId");
        if(newUserId == null)
        {
            // 如果创建失败,抛出异常
            throw new IllegalStateException("未知错误,注册失败");
        }
        // 如果创建成功,从map类型的结果里取出各字段,封装成查询结果DTO
        users newUserInfo = usersMapper.getUserInfoById(newUserId);
        UserDTO newUserDTO = new UserDTO(
                newUserInfo.getId(),
                newUserInfo.getUsername(),
                newUserInfo.getPassword(),
                newUserInfo.getRegistrationTimestamp(),
                newUserInfo.getUserDirectoryId(),
                newUserInfo.getFavoritesFolderId(),
                newUserInfo.getQuotaLimit());
        return newUserDTO;
    }

    // User login logic
    @Override
    public UserDTO loginUser(String username, String password, HttpSession session) throws IncorrectPasswordException {
        // TODO 实现service层
        // 首先检查session是否为已经登录的用户,若已经登录,拒绝该请求,抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId != null)
        {
            throw new IllegalStateException("已经登录,需退出登录再登录");
        }
        // 然后调用dao层的userLogin方法,返回一个pojo,如果查询不到任何结果,抛出异常
        users usersInfo = usersMapper.getUserInfoByUsername(username);
        String userPassword = usersInfo.getPassword();
        // 比较用户密码和输入是否一致,输入不一致抛出异常
        if(userPassword.equals(password))
        {
            SessionManager.setLoginInfo(sessionId,usersInfo.getId());
            // TODO 输入一致,封装VO作为返回
            UserDTO UserDTO = new UserDTO(
                    usersInfo.getId(),
                    usersInfo.getUsername(),
                    usersInfo.getPassword(),
                    usersInfo.getRegistrationTimestamp(),
                    usersInfo.getUserDirectoryId(),
                    usersInfo.getFavoritesFolderId(),
                    usersInfo.getQuotaLimit());
            return UserDTO;
        }
        else
        {
            throw new IncorrectPasswordException("密码错误");
        }
    }

    // User logout logic
    @Override
    public boolean logoutUser(HttpSession session) {
        // 检查用户的session,如果未登录,抛出异常,如果登录,使session失效,清除id到session的映射
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null)
        {
            throw new IllegalStateException("尚未登录,无需退出登录");
        }
        else
        {
            SessionManager.removeLoginInfo(sessionId);
            return true;
        }
    }

    // Get user information by ID
    @Override
    public UserDTO getUserInfoById(int TargetUserId,HttpSession session) {
        // Set the properties of the user object based on the retrieved information
        // 检查用户的session,如果未登录,抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null)
        {
            throw new IllegalStateException("必须先登录才可查看用户信息");
        }
        // 调用dao层的getUserInfoById方法,得到
        users targetUserInfo = usersMapper.getUserInfoById(TargetUserId);
        // TODO 把用户的公开信息封装成VO
        UserDTO UserDTO = new UserDTO(
                targetUserInfo.getId(),
                targetUserInfo.getUsername(),
                targetUserInfo.getPassword(),
                targetUserInfo.getRegistrationTimestamp(),
                targetUserInfo.getUserDirectoryId(),
                targetUserInfo.getFavoritesFolderId(),
                targetUserInfo.getQuotaLimit());
        return UserDTO;
    }
}