package com.example.api.service.impl;

import com.example.api.dao.FilesMapper;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import com.example.api.service.UploadService;
import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FilesMapper filesMapper;

    @Transactional
    @Override
    public boolean file(Files files, Files pfiles, String blob, Users users) {
        //1.获取当前的目录 父文件id
        //2.获取文件名
        //3.文件的内容  blob对象 直接由存储过程完成
        //4.将user设置为创建者
        try {
            int fileId = filesMapper.proc_File_Upload(files.getFileName(), users.getUserId(), pfiles.getFileId(), blob.getBytes());
            //匹配正则表达式 选择icon
            Regex regex = new Regex("*.txt");
            boolean matches = files.getFileName().matches(regex.toString());
            if(matches) {
                filesMapper.proc_File_SetIcon(fileId, 1, null);
            } else {
                filesMapper.proc_File_SetIcon(fileId, 2, null);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
