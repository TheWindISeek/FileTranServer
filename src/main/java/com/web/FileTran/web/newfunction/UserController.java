package com.web.FileTran.web.newfunction;

import com.web.FileTran.newservice.FileService;
import com.web.FileTran.newservice.FolderService;
import com.web.FileTran.newvo.UserVO;
import com.web.FileTran.newservice.UserService;
import com.web.FileTran.newvo.FileVO;
import com.web.FileTran.newvo.FolderContentVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final FileService fileService;
    private final FolderService folderService;

    // Constructor with dependency injection

    public UserController(UserService userService, FileService fileService, FolderService folderService) {
        this.userService = userService;
        this.fileService = fileService;
        this.folderService = folderService;
    }

    // Endpoint for querying user information
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserVO> getUserInfo(@PathVariable long userId, HttpSession session) {
        try {
            UserVO user = userService.getUserById(userId,session);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint for querying folder content
    @GetMapping("/folder/{folderId}/content")
    public ResponseEntity<FolderContentVO> getFolderContent(
            @PathVariable long folderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            HttpSession session) {
        try {
            FolderContentVO folderContent = folderService.getFolderContent(folderId, page, pageSize,session);
            return ResponseEntity.ok(folderContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint for querying file information
    @GetMapping("/file/{fileId}")
    public ResponseEntity<FileVO> getFileInfo(@PathVariable long fileId, HttpSession session) {
        try {
            FileVO file = fileService.getFileById(fileId,session);
            return ResponseEntity.ok(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}