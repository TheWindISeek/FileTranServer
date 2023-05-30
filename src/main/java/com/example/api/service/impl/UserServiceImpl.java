package com.example.api.service.impl;

import com.example.api.dao.UsersMapper;
import com.example.api.pojo.Users;
import com.example.api.service.UserService;
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
        if(users1.getUserName() == users.getUserName()
            && users1.getUserPassword() == users.getUserPassword())
            return true;
        return false;
    }

    @Transactional
    @Override
    public int register(Users users) {
        int id = 0;
        try {
            id = usersMapper.insertSelective(users);
        }catch (Exception exception) {
            exception.printStackTrace();
            return -1;
        }
        return id;
    }
}
