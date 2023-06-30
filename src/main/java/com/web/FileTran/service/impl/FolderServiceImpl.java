package com.web.FileTran.service.impl;

import com.web.FileTran.dao.foldersMapper;
import com.web.FileTran.dto.FolderContentDTO;
import com.web.FileTran.dto.FolderDTO;
import com.web.FileTran.exception.Folder_FileExceptions.FolderNotFoundException;
import com.web.FileTran.exception.Folder_FileExceptions.InsufficientPermissionException;
import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import com.web.FileTran.manager.SessionManager;
import com.web.FileTran.pojo.files;
import com.web.FileTran.pojo.folders;
import com.web.FileTran.service.FolderService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class FolderServiceImpl implements FolderService {
    private foldersMapper foldersMapper;
    @Override
    public FolderDTO getFolderInfoByID(long folderId, HttpSession session) throws FolderNotFoundException, LoginInfoException, InsufficientPermissionException {
        // 检查文件夹是否存在,若不存在抛出异常
        boolean folderExist = foldersMapper.checkFolderExists(folderId);
        if(!folderExist)
        {
            throw new FolderNotFoundException("File Not Found:"+folderId);
        }
        // 权限检查 通过dao层查询获得folder信息,然后需要根据权限确定是否把这条信息发给用户
        folders folderInfo = foldersMapper.getFolderInfo(folderId);
        String permission = folderInfo.getPermission();
        boolean publicPermission = "Writable".equals(permission);
        boolean onlyReadPermission = "Readable".equals(permission);
        boolean privatePermission = "Private".equals(permission);
        boolean inheritPermission = "Inherited".equals(permission);
        // 当前文件夹的权限为继承时,直接检查继承来源的权限
        long parentFolderId = folderInfo.getInheritedFromFolderId();
        if(inheritPermission)
        {
            try {
                folders parentFolderInfo = foldersMapper.getFolderInfo(parentFolderId);
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
            long creatorId = folderInfo.getCreatorId();
            // 私有权限时的非创建者
            if(userIdConvert != creatorId)
            {throw new InsufficientPermissionException("用户无权限");}
        }
        else
        {
            throw new RuntimeException("错误的权限类型");
        }
        // 检查是否具有权限,若无权限抛出异常,权限为公开的文件夹允许未登录的session查看
        // TODO 从dao层调用方法获取相关信息,map类型(这里也许应该改成获取pojo类型的结果),从map类型的结果里取出各字段,封装成查询结果DTO
        FolderDTO FolderDTO = new FolderDTO();
        return null;
    }

    // Get folder content by ID, page, and page size
    @Override
    public FolderContentDTO getFolderContent(long folderId, int page, int pageSize, HttpSession session) throws FolderNotFoundException, InsufficientPermissionException, LoginInfoException {
        // TODO 实现service层
        // 检查文件夹是否存在,若不存在抛出异常
        boolean folderExist = foldersMapper.checkFolderExists(folderId);
        if(!folderExist)
        {
            throw new FolderNotFoundException("File Not Found:"+folderId);
        }
        // 权限检查 通过dao层查询获得folder信息,然后需要根据权限确定是否把这条信息发给用户
        folders folderInfo = foldersMapper.getFolderInfo(folderId);
        String permission = folderInfo.getPermission();
        boolean publicPermission = "Writable".equals(permission);
        boolean onlyReadPermission = "Readable".equals(permission);
        boolean privatePermission = "Private".equals(permission);
        boolean inheritPermission = "Inherited".equals(permission);
        // 当前文件夹的权限为继承时,直接检查继承来源的权限
        long parentFolderId = folderInfo.getInheritedFromFolderId();
        if(inheritPermission)
        {
            try {
                folders parentFolderInfo = foldersMapper.getFolderInfo(parentFolderId);
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
            long creatorId = folderInfo.getCreatorId();
            // 私有权限时的非创建者
            if(userIdConvert != creatorId)
            {throw new InsufficientPermissionException("用户无权限");}
        }
        else if(privatePermission)
        {
            if(userId == null){throw new LoginInfoException("用户未登录");}
            long userIdConvert = userId;
            long creatorId = folderInfo.getCreatorId();
            // 私有权限时的非创建者
            if(userIdConvert != creatorId)
            {throw new InsufficientPermissionException("用户无权限");}
        }
        else
        {
            throw new RuntimeException("错误的权限类型");
        }
        // 检查是否具有权限,若无权限抛出异常,权限为公开的文件允许未登录的session查看
        // 如需登录,检查session是否为已登录的用户,若未登录抛出异常
        // TODO 从dao层调用两个方法获取相关信息,都返回list类型,将两个list合并成object类型列表,封装成查询结果DTO
        // 计算两个方法的起始位置,默认优先显示文件夹
        // 先分别得到子文件和子文件夹的总数,再计算分别需要获取多长的列表,再获取列表和合并
        // getChildrenFolders
        // getChildrenFiles
        return null;
    }
}
