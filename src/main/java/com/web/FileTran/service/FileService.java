package com.web.FileTran.service;


import com.web.FileTran.dto.DownloadInfoDTO;
import com.web.FileTran.dto.FileDTO;
import com.web.FileTran.dao.filesMapper;
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
    public FileDTO getFileInfoByID(long fileId, HttpSession session);

    /**
     * 使用文件id下载
     * @param fileId 文件id
     * @param session 会话
     * @return 下载 信息传输类
     */
    public DownloadInfoDTO getDownloadInfo(long fileId, HttpSession session);
}
