package com.example.api.service;

import com.example.api.pojo.Files;
import com.example.api.pojo.Users;

public interface UpdateService {
    /**
     * 更新指定用户的指定文件的信息
     * @param users 给定的用户
     * @param files 给定的文件
     * @return 是否更新成功
     */
    boolean file(Users users, Files files);
}
