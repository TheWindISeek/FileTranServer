package com.web.FileTran.service.impl;

import com.web.FileTran.dto.FolderContentDTO;
import com.web.FileTran.dto.FolderDTO;
import com.web.FileTran.service.FolderService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class FolderServiceImpl implements FolderService {
    @Override
    public FolderDTO getFolderInfoByID(long folderId, HttpSession session) {
        // TODO 实现service层
        // 检查文件夹是否存在,若不存在抛出异常
        // 检查是否具有权限,若无权限抛出异常,权限为公开的文件夹允许未登录的session查看
        // 如需登录,检查session是否为已登录的用户,若未登录抛出异常
        // 从dao层调用方法获取相关信息,map类型(这里也许应该改成获取pojo类型的结果),从map类型的结果里取出各字段,封装成查询结果DTO
        return null;
    }

    // Get folder content by ID, page, and page size
    @Override
    public FolderContentDTO getFolderContent(long folderId, int page, int pageSize, HttpSession session) {
        // TODO 实现service层
        // 检查文件夹是否存在,若不存在抛出异常
        // 检查是否具有权限,若无权限抛出异常,权限为公开的文件允许未登录的session查看
        // 如需登录,检查session是否为已登录的用户,若未登录抛出异常
        // 从dao层调用两个方法获取相关信息,都返回list类型,将两个list合并成object类型列表,封装成查询结果DTO
        return null;
    }
}
