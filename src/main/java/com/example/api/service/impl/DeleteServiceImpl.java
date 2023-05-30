package com.example.api.service.impl;

import com.example.api.dao.FilesMapper;
import com.example.api.dao.UsersMapper;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import com.example.api.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteServiceImpl implements DeleteService {

    @Autowired
    private FilesMapper filesMapper;
    /**
     * 删除对应用户的文件 其实本质上是引用数减1
     *
     * @param users 给定的用户
     * @param files 给定的文件
     * @param pfile 给定的父文件
     * @return 删除是否成功
     */
    @Transactional
    @Override
    public boolean fileFromUser(Users users, Files files, Files pfile) {
        //由于并不存在用户和file之间的表
        //因此删除操作分为两部
        //1.删除用户对files的引用 由于用户表中也没有存储files相关 因此需要删除的话 则需要从用户的不同文件夹之间删除
         try {
             if(pfile.getFileCreator() != users.getUserId())
                 return false;
             //删除父文件和子文件的关联
             filesMapper.proc_File_Dereference(files.getFileId(), pfile.getFileId());
             //2.删除files的引用次数
             files.setFileReferenceCount(files.getFileReferenceCount()-1);
             filesMapper.updateByPrimaryKeySelective(files);
         }catch (Exception exception) {
             return false;
         }
         return true;
    }
}
