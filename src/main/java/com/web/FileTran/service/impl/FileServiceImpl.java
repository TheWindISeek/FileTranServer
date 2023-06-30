package com.web.FileTran.service.impl;


import com.web.FileTran.dao.filesMapper;
import com.web.FileTran.dao.foldersMapper;
import com.web.FileTran.dto.DownloadInfoDTO;
import com.web.FileTran.dto.FileDTO;
import com.web.FileTran.exception.Folder_FileExceptions.FileNotFoundException;
import com.web.FileTran.exception.Folder_FileExceptions.InsufficientPermissionException;
import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import com.web.FileTran.manager.SessionManager;
import com.web.FileTran.pojo.files;
import com.web.FileTran.pojo.folders;
import com.web.FileTran.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    filesMapper filesMapper;
    @Autowired
    foldersMapper foldersMapper;
    // Get file information by ID
    @Override
    public FileDTO getFileInfoByID(long fileId, HttpSession session) throws FileNotFoundException, LoginInfoException, InsufficientPermissionException {
        // 检查文件是否存在,若不存在抛出异常
        boolean fileExist = filesMapper.checkFileExists(fileId);
        if(!fileExist)
        {
            throw new FileNotFoundException("File Not Found:"+fileId);
        }
        // 权限检查 通过dao层查询获得files信息,然后需要根据权限确定是否把这条信息发给用户
        files fileInfo = filesMapper.getFileInfo(fileId);
        String permission = fileInfo.getPermission();
        boolean publicPermission = "Writable".equals(permission);
        boolean onlyReadPermission = "Readable".equals(permission);
        boolean privatePermission = "Private".equals(permission);
        boolean inheritPermission = "Inherited".equals(permission);
        // 当前文件的权限为继承时,继续检查父文件夹的权限
        long parentFolderId = fileInfo.getFolderId();
        if(inheritPermission)
        {
            try {
                folders parentFolderInfo = foldersMapper.getFolderInfo(parentFolderId);
                permission = parentFolderInfo.getPermission();
                publicPermission = "Writable".equals(permission);
                onlyReadPermission = "Readable".equals(permission);
                privatePermission = "Private".equals(permission);
                inheritPermission = "Inherited".equals(permission);
                //父文件夹的权限为继承时,直接获得父文件夹继承来源的权限
                parentFolderId = parentFolderInfo.getInheritedFromFolderId();
                if(inheritPermission)
                {
                    try {
                        parentFolderInfo = foldersMapper.getFolderInfo(parentFolderId);
                        permission = parentFolderInfo.getPermission();
                        publicPermission = "Writable".equals(permission);
                        onlyReadPermission = "Readable".equals(permission);
                        privatePermission = "Private".equals(permission);
                        //这一层一定能得到权限级别
                        }
                    catch (Exception e) {
                        publicPermission = true;
                        onlyReadPermission = false;
                        privatePermission = false;
                    }
                }
            }
            catch (Exception e) {
                publicPermission = true;
                onlyReadPermission = false;
                privatePermission = false;
            }
        }
        // 获取用户并比较,如果不是公开权限就会抛出异常
        String sessionId = session.getId();
        Long userId = SessionManager.getUserIdBySessionId(sessionId);
        if(publicPermission)
        {
        }
        else if(onlyReadPermission)
        {
            if(userId == null){throw new LoginInfoException("用户未登录");}
        }
        else if(privatePermission)
        {
            if(userId == null){throw new LoginInfoException("用户未登录");}
            long userIdConvert = userId;
            long creatorId = fileInfo.getCreatorId();
            // 私有权限时的非创建者
            if(userIdConvert != creatorId)
            {throw new InsufficientPermissionException("用户无权限");}
        }
        else
        {
            throw new RuntimeException("错误的权限类型");
        }
        // 从dao层调用方法获取相关信息,map类型(这里也许应该改成获取pojo类型的结果),从map类型的结果里取出各字段,封装成查询结果DTO
        FileDTO fileDTO = new FileDTO();
        return fileDTO;
    }

    @Override
    public DownloadInfoDTO getDownloadInfo(long fileId, HttpSession session) throws FileNotFoundException, InsufficientPermissionException, LoginInfoException {
        // 检查文件是否存在,若不存在抛出异常
        boolean fileExist = filesMapper.checkFileExists(fileId);
        if(!fileExist)
        {
            throw new FileNotFoundException("File Not Found:"+fileId);
        }
        // 权限检查 通过dao层查询获得files信息,然后需要根据权限确定是否把这条信息发给用户
        files fileInfo = filesMapper.getFileInfo(fileId);
        String permission = fileInfo.getPermission();
        boolean publicPermission = "Writable".equals(permission);
        boolean onlyReadPermission = "Readable".equals(permission);
        boolean privatePermission = "Private".equals(permission);
        boolean inheritPermission = "Inherited".equals(permission);
        // 当前文件的权限为继承时,继续检查父文件夹的权限
        long parentFolderId = fileInfo.getFolderId();
        if(inheritPermission)
        {
            try {
                folders parentFolderInfo = foldersMapper.getFolderInfo(parentFolderId);
                permission = parentFolderInfo.getPermission();
                publicPermission = "Writable".equals(permission);
                onlyReadPermission = "Readable".equals(permission);
                privatePermission = "Private".equals(permission);
                inheritPermission = "Inherited".equals(permission);
                //父文件夹的权限为继承时,直接获得父文件夹继承来源的权限
                parentFolderId = parentFolderInfo.getInheritedFromFolderId();
                if(inheritPermission)
                {
                    try {
                        parentFolderInfo = foldersMapper.getFolderInfo(parentFolderId);
                        permission = parentFolderInfo.getPermission();
                        publicPermission = "Writable".equals(permission);
                        onlyReadPermission = "Readable".equals(permission);
                        privatePermission = "Private".equals(permission);
                        //这一层一定能得到权限级别
                    }
                    catch (Exception e) {
                        publicPermission = true;
                        onlyReadPermission = false;
                        privatePermission = false;
                    }
                }
            }
            catch (Exception e) {
                publicPermission = true;
                onlyReadPermission = false;
                privatePermission = false;
            }
        }
        // 获取用户并比较,如果不是公开权限就会抛出异常
        String sessionId = session.getId();
        Long userId = SessionManager.getUserIdBySessionId(sessionId);
        if(publicPermission)
        {
        }
        else if(onlyReadPermission)
        {
            if(userId == null){throw new LoginInfoException("用户未登录");}
            long userIdConvert = userId;
            long creatorId = fileInfo.getCreatorId();
            // 只读权限时的非创建者
            if(userIdConvert != creatorId)
            {throw new InsufficientPermissionException("用户无权限");}
        }
        else if(privatePermission)
        {
            if(userId == null){throw new LoginInfoException("用户未登录");}
            long userIdConvert = userId;
            long creatorId = fileInfo.getCreatorId();
            // 私有权限时的非创建者
            if(userIdConvert != creatorId)
            {throw new InsufficientPermissionException("用户无权限");}
        }
        else
        {
            throw new RuntimeException("错误的权限类型");
        }
        // TODO 从dao层调用方法获取相关信息,map类型,从map类型的结果里取出各字段,封装成查询结果DTO
        // DownloadInfoDTO DownloadInfoDTO = new DownloadInfoDTO();
        return null;
    }
}
