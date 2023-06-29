package com.web.FileTran.web.newfunction;

import com.web.FileTran.newdto.CommentDTO;
import com.web.FileTran.newvo.CommentVO;
import com.web.FileTran.newservice.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    CommentService commentService;

    @PostMapping("/addToFile")
    public ResponseEntity<CommentVO> addCommentToFile(@RequestParam("fileId") int fileId,
                                                      @RequestParam("parentCommentId") int parentCommentId,
                                                      @RequestParam("commentMessage") String commentMessage,
                                                      HttpSession session) {
        // TODO 根据session进行检测,对未登录用户重定向
        // TODO 调用service层
        CommentDTO commentDTO = commentService.addCommentToFile(fileId,parentCommentId,commentMessage,session);
        // DTO转为VO
        return null;
    }

    @PostMapping("/addToFolder")
    public ResponseEntity<CommentVO> addCommentToFolder(
            @RequestParam("folderId") int folderId,
            @RequestParam("parentCommentId") int parentCommentId,
            @RequestParam("commentMessage") String commentMessage,
            HttpSession session) {
        // TODO 根据session进行检测,对未登录用户重定向
        // TODO 调用service层
        CommentDTO commentDTO = commentService.addCommentToFolder(folderId,parentCommentId,commentMessage,session);
        // DTO转为VO
        return null;
    }

    @PostMapping("/replyToComment")
    public ResponseEntity<CommentVO> replyToComment(
            @RequestParam("parentCommentId") int parentCommentId,
            @RequestParam("commentMessage") String commentMessage,
            HttpSession session) {
        // TODO 根据session进行检测,对未登录用户重定向
        // TODO 调用service层
        CommentDTO commentDTO = commentService.replyToComment(parentCommentId,commentMessage,session);
        // DTO转为VO
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<CommentVO> updateComment(
            @RequestParam("commentId") int commentId,
            @RequestParam("commentMessage") String commentMessage,
            HttpSession session) {
        // TODO 根据session进行检测,对未登录用户重定向
        // TODO 调用service层
        CommentDTO commentDTO = commentService.updateComment(commentId,commentMessage,session);
        // DTO转为VO
        return null;
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteComment(
            @RequestParam("commentId") int commentId,
            HttpSession session) {
        // TODO 根据session进行检测,对未登录用户重定向
        // TODO 调用service层
        boolean delete_finish = commentService.deleteComment(commentId,session);
        // DTO转为VO
        return null;
    }
}