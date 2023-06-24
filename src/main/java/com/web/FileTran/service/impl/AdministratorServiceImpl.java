package com.web.FileTran.service.impl;

import com.web.FileTran.dao.AdministratorMapper;
import com.web.FileTran.pojo.dto.AdmAccountLogin_Result;
import com.web.FileTran.pojo.po.Administrator;
import com.web.FileTran.service.AdministratorService;

import javax.servlet.http.HttpSession;

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
            if (administrator1.getAdmName() == administrator.getAdmName()
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
        return id;
    }

//    @Override
//    public AdmAccountLogin_Result AdmAccountLogin(HttpSession session, String Adm_Name, String Adm_Password) {
//        // TODO 自动生成的方法存根
//        /*新的登录方法,从数据库查找是否有符合条件的账号
//         * */
//        return null;
//    }
}
