package com.web.FileTran.service;

import com.web.FileTran.dto.FolderContentDTO;
import com.web.FileTran.dto.FolderDTO;
import com.web.FileTran.exception.Folder_FileExceptions.FileNotFoundException;
import com.web.FileTran.exception.Folder_FileExceptions.FolderNotFoundException;
import com.web.FileTran.exception.Folder_FileExceptions.InsufficientPermissionException;
import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public interface FolderService {
    /**
     * Get folder Info by ID
     * @param folderId 文件夹id
     * @param session 会话
     * @return 文件夹 信息传输类
     */
    FolderDTO getFolderInfoByID(int folderId, HttpSession session) throws FolderNotFoundException, LoginInfoException, InsufficientPermissionException;

    /**
     * Get folder content by ID, page, and page size
     * @param folderId 文件夹id
     * @param page 页号
     * @param pageSize 每页大小
     * @param session 会话
     * @return 文件夹 信息传输类
     */
    FolderContentDTO getFolderContent(int folderId, int page, int pageSize, HttpSession session) throws FolderNotFoundException, InsufficientPermissionException, LoginInfoException;
}
