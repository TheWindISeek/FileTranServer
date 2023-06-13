package com.web.FileTran.service.impl;

import com.web.FileTran.dao.UsersMapper;
import com.web.FileTran.pojo.dto.UserAccountLogin_Result;
import com.web.FileTran.pojo.dto.UserAccountRegister_Result;
import com.web.FileTran.pojo.po.Users;
import com.web.FileTran.service.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Transactional
    @Override
    public boolean login(Users users) {
        Users users1 = usersMapper.selectByPrimaryKey(users.getUserId());
        if (users1.getUserName() == users.getUserName() && users1.getUserPassword() == users.getUserPassword()) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public int register(Users users) {
        int id = 0;
        try {
            id = usersMapper.insertSelective(users);
        } catch (Exception exception) {
            exception.printStackTrace();
            return -1;
        }
        return id;
    }
//
//    @Override
//    public UserAccountLogin_Result UserAccountLogin(HttpSession session, String User_Name, String User_Password) {
//        // TODO 自动生成的方法存根
//        return null;
//    }
//
//    @Override
//    public UserAccountRegister_Result UserAccountRegister(HttpSession session, String User_Name, String User_Password) {
//        // TODO 自动生成的方法存根
//        return null;
//    }
}
