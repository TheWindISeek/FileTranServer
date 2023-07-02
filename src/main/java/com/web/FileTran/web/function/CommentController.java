package com.web.FileTran.web.function;

import com.web.FileTran.dto.CommentDTO;
import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import com.web.FileTran.manager.SessionManager;
import com.web.FileTran.vo.CommentVO;
import com.web.FileTran.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/addToFile")
    public ResponseEntity<CommentVO> addCommentToFile(@RequestParam("fileId") int fileId,
                                                      @RequestParam("commentMessage") String commentMessage,
                                                      HttpSession session) {
        // 根据session进行检测,对未登录用户重定向
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null) {
            // 无权限
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            // 调用service层
            CommentDTO commentDTO = commentService.addCommentToFile(fileId,commentMessage,session);
            return ResponseEntity.ok(commentDTO.convertToCommentVO());
        }
        catch (LoginInfoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addToFolder")
    public ResponseEntity<CommentVO> addCommentToFolder(
            @RequestParam("folderId") int folderId,
            @RequestParam("commentMessage") String commentMessage,
            HttpSession session) {
        // 根据session进行检测,对未登录用户重定向
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null) {
            // 无权限
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 调用service层
        try {
            CommentDTO commentDTO = commentService.addCommentToFolder(folderId,commentMessage,session);
            return ResponseEntity.ok(commentDTO.convertToCommentVO());
        }
        catch (LoginInfoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/replyToComment")
    public ResponseEntity<CommentVO> replyToComment(
            @RequestParam("parentCommentId") int parentCommentId,
            @RequestParam("commentMessage") String commentMessage,
            HttpSession session) {
        // 根据session进行检测,对未登录用户重定向
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null) {
            // 无权限
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 调用service层
        try {
            CommentDTO commentDTO = commentService.replyToComment(parentCommentId,commentMessage,session);
            return ResponseEntity.ok(commentDTO.convertToCommentVO());
        }
        catch (LoginInfoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CommentVO> updateComment(
            @RequestParam("commentId") int commentId,
            @RequestParam("commentMessage") String commentMessage,
            HttpSession session) {
        // 根据session进行检测,对未登录用户重定向
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null) {
            // 无权限
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 调用service层
        try {
            CommentDTO commentDTO = commentService.updateComment(commentId,commentMessage,session);
            return ResponseEntity.ok(commentDTO.convertToCommentVO());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /*
        catch (LoginInfoException e) {
            throw new RuntimeException(e);
        }
        catch (UnauthorizedCommentOperationException e) {
            throw new RuntimeException(e);
        }
        */
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteComment(
            @RequestParam("commentId") int commentId,
            HttpSession session) {
        // 根据session进行检测,对未登录用户重定向
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null) {
            // 无权限
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 调用service层
        try {
            boolean delete_finish = commentService.deleteComment(commentId,session);
            return ResponseEntity.ok(delete_finish);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /*
        catch (LoginInfoException e) {
            throw new RuntimeException(e);
        }
        catch (UnauthorizedCommentOperationException e) {
            throw new RuntimeException(e);
        }
         */
    }
}
