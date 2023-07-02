package com.web.FileTran.service.impl;

import com.web.FileTran.dao.commentsMapper;
import com.web.FileTran.dto.CommentDTO;
import com.web.FileTran.exception.CommentExceptions.UnauthorizedCommentOperationException;
import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import com.web.FileTran.manager.SessionManager;
import com.web.FileTran.pojo.comments;
import com.web.FileTran.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private commentsMapper commentsMapper;

    // 在文件下发布留言
    @Transactional
    @Override
    public CommentDTO addCommentToFile(int fileId, String commentMessage, HttpSession session) throws LoginInfoException {
        // 检查session,得到用户id,如果没登录会抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null)
        {
            throw new LoginInfoException("用户未登录");
        }
        // 调用dao层创建留言,封装DTO并返回
        Map<String, Object> params = new HashMap<>();
        params.put("fileId", fileId);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.addCommentToFile(params);
        int commentId = (int)params.get("commentId");
        String errorMessage = (String)params.get("errorMessage");
        boolean success = "".equals(errorMessage);
        if(success)
        {
            comments commentInfo= commentsMapper.getCommentInfo(commentId);
            return commentInfo.convertToCommentDTO();
        }
        else
        {
            throw new RuntimeException(errorMessage);
        }
    }

    // 在文件夹下发布留言
    @Transactional
    @Override
    public CommentDTO addCommentToFolder(int folderId, String commentMessage, HttpSession session) throws LoginInfoException {
        // 检查session,得到用户id,如果没登录会抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null)
        {
            throw new LoginInfoException("用户未登录");
        }
        // 调用dao层创建留言,封装DTO并返回
        Map<String, Object> params = new HashMap<>();
        params.put("folderId", folderId);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.addCommentToFolder(params);
        int commentId = (int)params.get("commentId");
        String errorMessage = (String)params.get("errorMessage");
        boolean success = "".equals(errorMessage);
        if(success)
        {
            comments commentInfo= commentsMapper.getCommentInfo(commentId);
            return commentInfo.convertToCommentDTO();
        }
        else
        {
            throw new RuntimeException(errorMessage);
        }
    }

    // 对某条留言进行回复
    @Transactional
    @Override
    public CommentDTO replyToComment(int parentCommentId, String commentMessage, HttpSession session) throws LoginInfoException {
        // 检查session,得到用户id,如果没登录会抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null)
        {
            throw new LoginInfoException("用户未登录");
        }

        // 调用dao层创建留言,封装DTO并返回
        Map<String, Object> params = new HashMap<>();
        params.put("parentCommentId", parentCommentId);
        params.put("userId", userId);
        params.put("commentMessage", commentMessage);
        commentsMapper.replyToComment(params);
        int commentId = (int)params.get("commentId");
        String errorMessage = (String)params.get("errorMessage");
        boolean success = "".equals(errorMessage);
        if(success)
        {
            comments commentInfo= commentsMapper.getCommentInfo(commentId);
            return commentInfo.convertToCommentDTO();
        }
        else
        {
            throw new RuntimeException(errorMessage);
        }
    }

    // 修改留言
    @Transactional
    @Override
    public CommentDTO updateComment(int commentId, String commentMessage, HttpSession session) throws LoginInfoException, UnauthorizedCommentOperationException {
        // 检查session,得到用户id,如果没登录会抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null)
        {
            throw new LoginInfoException("用户未登录");
        }
        // 确认留言发布者和用户id一致,否则抛出无权限异常
        comments comment = commentsMapper.getCommentInfo(commentId);
        int comment_creator = comment.getUserId();
        int userIdConvert = userId.intValue();
        if(comment_creator != userIdConvert)
        {
            throw new UnauthorizedCommentOperationException("用户不一致");
        }
        // 调用dao层创建留言,封装DTO并返回
        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        params.put("commentMessage", commentMessage);
        commentsMapper.updateComment(params);
        String errorMessage = (String)params.get("errorMessage");
        boolean success = "".equals(errorMessage);
        if(success)
        {
            comments commentInfo= commentsMapper.getCommentInfo(commentId);
            return commentInfo.convertToCommentDTO();
        }
        else
        {
            throw new RuntimeException(errorMessage);
        }
    }

    // 删除留言
    @Transactional
    @Override
    public boolean deleteComment(int commentId, HttpSession session) throws LoginInfoException, UnauthorizedCommentOperationException {
        // 检查session,得到用户id,如果没登录会抛出异常
        String sessionId = session.getId();
        Integer userId = SessionManager.getUserIdBySessionId(sessionId);
        if(userId == null)
        {
            throw new LoginInfoException("用户未登录");
        }
        // 确认留言发布者和用户id一致,否则抛出无权限异常
        comments comment = commentsMapper.getCommentInfo(commentId);
        int comment_creator = comment.getUserId();
        int userIdConvert = userId.intValue();
        if(comment_creator != userIdConvert)
        {
            throw new UnauthorizedCommentOperationException("用户不一致");
        }
        // 调用dao层创建留言,封装DTO并返回
        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        commentsMapper.deleteComment(params);
        String errorMessage = (String)params.get("errorMessage");
        boolean success = "".equals(errorMessage);
        if(success)
        {
            return true;
        }
        else
        {
            throw new RuntimeException(errorMessage);
        }
    }
}
