package com.web.FileTran.web.newfunction;

import com.web.FileTran.dto.DownloadInfoDTO;
import com.web.FileTran.dto.FileDTO;
import com.web.FileTran.dto.FolderContentDTO;
import com.web.FileTran.dto.FolderDTO;
import com.web.FileTran.service.FileService;
import com.web.FileTran.service.FolderService;
import com.web.FileTran.vo.FileVO;
import com.web.FileTran.vo.FolderContentVO;
import com.web.FileTran.vo.FolderVO;
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
import java.sql.SQLException;

@RestController
@RequestMapping("/folder_files")
public class Folder_FileController {

    private final FileService fileService;
    private final FolderService folderService;

    // Constructor with dependency injection

    public Folder_FileController(FileService fileService, FolderService folderService) {
        this.fileService = fileService;
        this.folderService = folderService;
    }

    // Endpoint for querying file information
    @GetMapping("/folder/{fileId}/info")
    public ResponseEntity<FolderVO> getFolderInfo(
            @PathVariable long folderId,
            HttpSession session) {
        // 因为public权限的文件或文件夹不需要登录也允许查看,故全部权限检测在service层完成,本层只负责接收抛出的异常
        try {
            FolderDTO folderInfoDTO = folderService.getFolderInfoByID(folderId,session);
            FolderVO folderInfoVO = null;
            // TODO 将DTO转为VO并返回
            return ResponseEntity.ok(folderInfoVO);
        }
        catch (Exception e) {
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
            @PathVariable long fileId,
            HttpSession session) {
        // 因为public权限的文件或文件夹不需要登录也允许查看,故全部权限检测在service层完成,本层只负责接收抛出的异常
        try {
            FileDTO fileInfoDTO = fileService.getFileInfoByID(fileId,session);
            FileVO fileInfoVO = null;
            // TODO 将DTO转为VO并返回
            return ResponseEntity.ok(fileInfoVO);
        }
        catch (Exception e) {
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
            @PathVariable long fileId,
            HttpSession session) {
        // TODO 下载必须要登录,所以进入service层检测之前就应该检查用户登录状态,对未登录用户重定向
        // 需要实现下载功能
        // 调用Service层方法获取要下载的文件
        DownloadInfoDTO downloadInfo = fileService.getDownloadInfo(fileId,session);
        String fileName = downloadInfo.getFileName();
        Blob fileBlob = downloadInfo.getFileContent();
        Resource resource = null;
        try {
            InputStream blobBytes = fileBlob.getBinaryStream();
            // 将文件转换为Spring Resource
            resource = new FileSystemResource((Path) blobBytes);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /* TODO 细化异常类型
        catch (Exception e) {

        }
        */
        // 设置HTTP头部信息
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Endpoint for querying folder content
    @GetMapping("/folder/{folderId}/content")
    public ResponseEntity<FolderContentVO> getFolderContent(
            @PathVariable long folderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            HttpSession session) {
        // 因为public权限的文件或文件夹不需要登录也允许查看,故全部权限检测在service层完成,本层只负责接收抛出的异常
        try {
            FolderContentDTO folderContentDTO = folderService.getFolderContent(folderId, page, pageSize,session);
            // TODO 将DTO转为VO并返回
            FolderContentVO folderContentVO = null;
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