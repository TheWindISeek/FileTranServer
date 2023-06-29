package com.web.FileTran.newservice;

import com.web.FileTran.newdaogenerated.commentsMapper;
import com.web.FileTran.newdto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.web.FileTran.newservice.publicFun.SessionFunctions.getUserIdFromSession;

public class CommentService {
    @Autowired
    private commentsMapper commentsMapper;



    // 在文件下发布留言
    public CommentDTO addCommentToFile(int fileId, int parentCommentId, String commentMessage, HttpSession session)
    {
        // TODO

        int userId = getUserIdFromSession(session);
        Map<String, Object> params = new HashMap<>();
        params.put("fileId", fileId);
        params.put("parentCommentId", parentCommentId);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.addCommentToFile(params);
        return null;
    }

    // 在文件夹下发布留言
    public CommentDTO addCommentToFolder(int folderId, int parentCommentId, String commentMessage, HttpSession session)
    {
        // TODO

        int userId = getUserIdFromSession(session);
        Map<String, Object> params = new HashMap<>();
        params.put("folderId", folderId);
        params.put("parentCommentId", parentCommentId);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.addCommentToFolder(params);
        return null;
    }

    // 对某条留言进行回复
    public CommentDTO replyToComment(int parentCommentId, String commentMessage, HttpSession session)
    {
        // TODO
        int userId = getUserIdFromSession(session);
        Map<String, Object> params = new HashMap<>();
        params.put("parentCommentId", parentCommentId);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.replyToComment(params);

        return null;
    }

    // 修改留言
    public CommentDTO updateComment(int commentId, String commentMessage, HttpSession session)
    {
        // TODO
        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        params.put("commentMessage", commentMessage);

        commentsMapper.updateComment(params);
        return null;
    }

    // 删除留言
    public boolean deleteComment(int commentId, HttpSession session)
    {
        // TODO

        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        commentsMapper.deleteComment(params);

        return false;
    }
}
