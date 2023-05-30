package com.example.api.service;

import com.example.api.pojo.Files;
import com.example.api.pojo.Users;

/**
 * @author JeffreySharp
 * @apiNote 为upload controller 提供的服务接口
 */
public interface UploadService {
    /**
     * 上传指定的文件到服务器
     *
     * @param files  给定的文件
     * @param pfiles
     * @param blob
     * @param users
     * @return 是否上传成功
     */
    boolean file(Files files, Files pfiles, String blob, Users users);
}
