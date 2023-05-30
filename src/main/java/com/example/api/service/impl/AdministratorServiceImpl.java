package com.example.api.service.impl;

import com.example.api.dao.AdministratorMapper;
import com.example.api.pojo.Administrator;
import com.example.api.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Transactional
    @Override
    public boolean login(Administrator administrator) {
        try {
            Administrator administrator1 = administratorMapper.selectByPrimaryKey(administrator.getAdmId());
            if(administrator1.getAdmName() == administrator.getAdmName()
                    && administrator1.getAdmPassword() == administrator.getAdmPassword()) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return false;
    }

    @Transactional
    @Override
    public int register(Administrator administrator) {
        int id = 0;
        try {
            id = administratorMapper.insertSelective(administrator);
        } catch (Exception exception) {
            exception.printStackTrace();
            return -1;
        }
        return  id;
    }
}
