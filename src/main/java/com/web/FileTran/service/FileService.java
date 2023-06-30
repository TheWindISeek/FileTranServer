package com.web.FileTran.service;


import com.web.FileTran.dto.DownloadInfoDTO;
import com.web.FileTran.dto.FileDTO;
import com.web.FileTran.dao.filesMapper;
import com.web.FileTran.exception.Folder_FileExceptions.FileNotFoundException;
import com.web.FileTran.exception.Folder_FileExceptions.InsufficientPermissionException;
import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public interface FileService {
    /**
     * Get file information by ID
     * @param fileId 文件id
     * @param session 会话
     * @return 文件 信息传输类
     */
    public FileDTO getFileInfoByID(int fileId, HttpSession session) throws FileNotFoundException, LoginInfoException, InsufficientPermissionException;

    /**
     * 使用文件id下载
     * @param fileId 文件id
     * @param session 会话
     * @return 下载 信息传输类
     */
    public DownloadInfoDTO getDownloadInfo(int fileId, HttpSession session) throws FileNotFoundException, InsufficientPermissionException, LoginInfoException;
}
