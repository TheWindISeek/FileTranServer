package com.example.api.service.impl;

import com.example.api.dao.FilesMapper;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import com.example.api.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private FilesMapper filesMapper;
    /**
     *
     * @param users 给定的用户
     * @param files 给定的文件
     * @return
     */
    @Transactional
    @Override
    public boolean file(Users users, Files files) {
//        files.setFileCreator(users.getUserId());
        try {
            if(files.getFileCreator() != users.getUserId()) return false;

            //更新文件的信息
            filesMapper.updateByPrimaryKeySelective(files);
        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
