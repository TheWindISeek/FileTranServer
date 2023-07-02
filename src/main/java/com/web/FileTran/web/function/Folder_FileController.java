package com.web.FileTran.web.function;

import com.web.FileTran.dto.*;
import com.web.FileTran.manager.SessionManager;
import com.web.FileTran.service.FileService;
import com.web.FileTran.service.FolderService;
import com.web.FileTran.service.UserService;
import com.web.FileTran.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Blob;

@RestController
@RequestMapping("/folder_files")
public class Folder_FileController {

    @Autowired
    private final FileService fileService;
    @Autowired
    private final FolderService folderService;
    @Autowired
    private final UserService userService;

    // Constructor with dependency injection

    public Folder_FileController(FileService fileService, FolderService folderService, UserService userService) {
        this.fileService = fileService;
        this.folderService = folderService;
        this.userService = userService;
    }

    // Endpoint for querying file information
    @GetMapping("/folder/{folderId}/info")
    public ResponseEntity<FolderVO> getFolderInfo(
            @PathVariable int folderId,
            HttpSession session) {
        // 因为public权限的文件或文件夹不需要登录也允许查看,故全部权限检测在service层完成,本层只负责接收抛出的异常
        try {
            FolderDTO folderInfoDTO = folderService.getFolderInfoByID(folderId,session);
            // 根据创建者得到用户信息视图
            Integer creatorId = folderInfoDTO.getCreatorId();
            UserVO creatorVO = null;
            if(creatorId != null)
            {
                // 仅当文件创建者存在时创建子VO
                UserDTO creatorDTO = userService.getUserInfoById(creatorId,session);
                creatorVO = new UserVO(
                        creatorDTO.getId(),
                        creatorDTO.getUsername(),
                        creatorDTO.getUserDirectoryId(),
                        creatorDTO.getFavoritesFolderId()
                );
            }
            // 根据快捷方式目标得到快捷方式视图
            Integer shortcutId = folderInfoDTO.getShortcutDestination();
            ShortcutVO shortcutVO = null;
            if(shortcutId != null)
            {
                // 仅当是快捷方式时创建子VO
                FolderDTO targetFolderDTO = folderService.getFolderInfoByID(shortcutId,session);
                Integer targetCreatorId = targetFolderDTO.getCreatorId();
                UserVO targetCreatorVO = null;
                if(targetCreatorId != null)
                {
                    // 仅当目标创建者存在时获得VO
                    UserDTO targetCreatorDTO = userService.getUserInfoById(targetCreatorId,session);
                    targetCreatorVO = new UserVO(
                            targetCreatorDTO.getId(),
                            targetCreatorDTO.getUsername(),
                            targetCreatorDTO.getUserDirectoryId(),
                            targetCreatorDTO.getFavoritesFolderId()
                    );
                }
                shortcutVO = new ShortcutVO(targetFolderDTO.getId(),targetFolderDTO.getName(),targetCreatorVO);
            }
            // 把视图和其他信息组装
            FolderVO folderInfoVO = new FolderVO(
                    folderInfoDTO.getId(),
                    folderInfoDTO.getName(),
                    creatorVO,
                    folderInfoDTO.getFolderType(),
                    folderInfoDTO.getPermission(),
                    shortcutVO);
            // 将DTO转为VO并返回
            return ResponseEntity.ok(folderInfoVO);
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /* TODO 细化异常类型
        catch (Exception e) {

        }
        */
    }

    // Endpoint for querying file information
    @GetMapping("/file/{fileId}/info")
    public ResponseEntity<FileVO> getFileInfo(
            @PathVariable int fileId,
            HttpSession session) {
        // 因为public权限的文件或文件夹不需要登录也允许查看,故全部权限检测在service层完成,本层只负责接收抛出的异常
        try {
            FileDTO fileInfoDTO = fileService.getFileInfoByID(fileId,session);
            Integer creatorId = fileInfoDTO.getCreatorId();
            UserVO CreatorVO = null;
            if(creatorId != null)
            {
                UserDTO targetCreatorDTO = userService.getUserInfoById(creatorId,session);
                CreatorVO = new UserVO(targetCreatorDTO.getId(),targetCreatorDTO.getUsername(),targetCreatorDTO.getUserDirectoryId(),targetCreatorDTO.getFavoritesFolderId());
            }
            Integer shortcutId = fileInfoDTO.getShortcutDestination();
            ShortcutVO shortcutVO = null;
            if(shortcutId != null)
            {
                // 仅当是快捷方式时创建子VO
                FileDTO targetFileDTO = fileService.getFileInfoByID(shortcutId,session);
                Integer targetCreatorId = targetFileDTO.getCreatorId();
                UserVO targetCreatorVO = null;
                if(targetCreatorId != null)
                {
                    // 仅当目标创建者存在时获得VO
                    UserDTO targetCreatorDTO = userService.getUserInfoById(targetCreatorId,session);
                    targetCreatorVO = new UserVO(
                            targetCreatorDTO.getId(),
                            targetCreatorDTO.getUsername(),
                            targetCreatorDTO.getUserDirectoryId(),
                            targetCreatorDTO.getFavoritesFolderId()
                    );
                }
                shortcutVO = new ShortcutVO(targetFileDTO.getId(),targetFileDTO.getName(),targetCreatorVO);
            }
            // 将DTO转为VO并返回
            FileVO fileInfoVO = new FileVO(
                    fileInfoDTO.getId(),
                    fileInfoDTO.getName(),
                    CreatorVO,
                    fileInfoDTO.getFileType(),
                    fileInfoDTO.getPermission(),
                    shortcutVO,
                    null);
            // TODO commentsVO还未实现,这里使用null暂时代替
            return ResponseEntity.ok(fileInfoVO);
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /* TODO 细化异常类型
        catch (Exception e) {

        }
        */
    }

    // Endpoint for querying file information
    @GetMapping("/file/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable int fileId,
            HttpSession session) {
        // 下载必须要登录,所以进入service层检测之前就应该检查用户登录状态,对未登录用户重定向
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null) {
            // 无权限
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 需要实现下载功能
        // 调用Service层方法获取要下载的文件,得到DTO
        DownloadInfoDTO downloadInfo;
        try {
            downloadInfo = fileService.getDownloadInfo(fileId,session);
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /*
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (InsufficientPermissionException e) {
            throw new RuntimeException(e);
        }
        catch (LoginInfoException e) {
            throw new RuntimeException(e);
        }
        */
        // 从DTO拿到文件名和文件内容
        String fileName = downloadInfo.getFileName();
        Blob fileBlob = downloadInfo.getFileContent();
        Resource resource;
        try {
            InputStream blobBytes = fileBlob.getBinaryStream();
            // 将文件转换为Spring Resource
            resource = new FileSystemResource((Path) blobBytes);
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /* TODO 细化异常类型
        catch (Exception e) {

        }
        */
        // 设置HTTP头部信息
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        // 最后返回让用户下载
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Endpoint for querying folder content
    @GetMapping("/folder/{folderId}/content")
    public ResponseEntity<FolderContentVO> getFolderContent(
            @PathVariable int folderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            HttpSession session) {
        // 因为public权限的文件或文件夹不需要登录也允许查看,故全部权限检测在service层完成,本层只负责接收抛出的异常
        try {
            FolderContentDTO folderContentDTO = folderService.getFolderContent(folderId, page, pageSize,session);
            // 将DTO转为VO并返回
            FolderContentVO folderContentVO = new FolderContentVO(
                    folderContentDTO.getTotalItems(),
                    folderContentDTO.getCurrentPage(),
                    folderContentDTO.getFiles(),
                    folderContentDTO.getFolders()
            );
            return ResponseEntity.ok(folderContentVO);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /* TODO 细化异常类型
        catch (Exception e) {

        }
        */
    }
}