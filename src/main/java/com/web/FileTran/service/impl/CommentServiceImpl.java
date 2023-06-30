package com.web.FileTran.service.impl;

import com.web.FileTran.dao.commentsMapper;
import com.web.FileTran.dto.CommentDTO;
import com.web.FileTran.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.web.FileTran.manager.SessionManager.getUserIdFromSession;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private commentsMapper commentsMapper;

    // 在文件下发布留言

    @Transactional
    @Override
    public CommentDTO addCommentToFile(int fileId, String commentMessage, HttpSession session)
    {
        // TODO 检查session,得到用户id
        // TODO 如果没登录会抛出异常

        int userId = getUserIdFromSession(session);
        Map<String, Object> params = new HashMap<>();
        params.put("fileId", fileId);
        params.put("parentCommentId", null);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.addCommentToFile(params);
        return null;
    }

    // 在文件夹下发布留言
    @Transactional
    @Override
    public CommentDTO addCommentToFolder(int folderId, String commentMessage, HttpSession session)
    {
        // TODO

        int userId = getUserIdFromSession(session);
        Map<String, Object> params = new HashMap<>();
        params.put("folderId", folderId);
        params.put("parentCommentId", null);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.addCommentToFolder(params);
        return null;
    }

    // 对某条留言进行回复
    @Transactional
    @Override
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
    @Transactional
    @Override
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
    @Transactional
    @Override
    public boolean deleteComment(int commentId, HttpSession session)
    {
        // TODO

        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        commentsMapper.deleteComment(params);

        return false;
    }
}
