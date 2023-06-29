package com.web.FileTran.service;


import com.web.FileTran.dto.DownloadInfoDTO;
import com.web.FileTran.dto.FileDTO;
import com.web.FileTran.dao.filesMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public class FileService {
    filesMapper filesMapper;
    // Get file information by ID
    public FileDTO getFileInfoByID(long fileId, HttpSession session) {
        // TODO 实现service层
        // 检查文件是否存在,若不存在抛出异常
        boolean result = filesMapper.checkFileExists(fileId);
        // 检查是否具有权限,若无权限抛出异常,权限为公开的文件允许未登录的session查看
        // 如需登录,检查session是否为已登录的用户,若未登录抛出异常
        // 从dao层调用方法获取相关信息,map类型(这里也许应该改成获取pojo类型的结果),从map类型的结果里取出各字段,封装成查询结果DTO
        return null;
    }

    public DownloadInfoDTO getDownloadInfo(long fileId, HttpSession session)
    {
        // TODO 实现service层
        // 检查文件是否存在,若不存在抛出异常
        // 检查是否具有权限,若无权限抛出异常,权限为公开的文件允许未登录的session下载
        // 如需登录,检查session是否为已登录的用户,若未登录抛出异常
        // 从dao层调用方法获取相关信息,map类型,从map类型的结果里取出各字段,封装成查询结果DTO
        return null;
    }
}
