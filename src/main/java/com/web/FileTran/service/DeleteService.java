package com.web.FileTran.service;

import com.web.FileTran.pojo.po.Files;
import com.web.FileTran.pojo.po.Users;

/**
 * @author JeffreySharp
 * @apiNote 为delete controller 提供的服务接口
 */
public interface DeleteService {
    /**
     * 将文件从给定用户的列表中删除
     *
     * @param users 给定的用户
     * @param files 给定的文件
     * @param pfile
     * @return 是否成功删除
     */
    boolean fileFromUser(Users users, Files files, Files pfile);
}
