package com.web.FileTran.web.newfunction;

import com.web.FileTran.newdto.DownloadInfoDTO;
import com.web.FileTran.newdto.FileDTO;
import com.web.FileTran.newdto.FolderContentDTO;
import com.web.FileTran.newdto.FolderDTO;
import com.web.FileTran.newservice.FileService;
import com.web.FileTran.newservice.FolderService;
import com.web.FileTran.newvo.FileVO;
import com.web.FileTran.newvo.FolderContentVO;
import com.web.FileTran.newvo.FolderVO;
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
        try {
            FolderDTO folderInfoDTO = FolderService.getFolderInfoByID(folderId,session);
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
    public ResponseEntity<FileVO> getFileInfo(@PathVariable long fileId, HttpSession session) {
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
    public ResponseEntity<Resource> downloadFile(@PathVariable long fileId, HttpSession session) {
        //需要实现下载功能
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