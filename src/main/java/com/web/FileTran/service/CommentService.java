package com.web.FileTran.service;

import com.web.FileTran.dto.CommentDTO;
import com.web.FileTran.exception.CommentExceptions.UnauthorizedCommentOperationException;
import com.web.FileTran.exception.UserExceptions.LoginInfoException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface CommentService {

    /**
     * 在文件下发布留言
     * @param fileId 文件id
     * @param commentMessage 留言内容
     * @param session 会话
     * @return 留言 信息传输类
     */
    CommentDTO addCommentToFile(int fileId, String commentMessage, HttpSession session) throws LoginInfoException;

    /**
     * 在文件夹下发布留言
     * @param folderId 文件夹id
     * @param commentMessage 留言内容
     * @param session 会话
     * @return 留言 信息传输类
     */
    CommentDTO addCommentToFolder(int folderId, String commentMessage, HttpSession session) throws LoginInfoException;

    /**
     * 对某条留言进行回复
     * @param parentCommentId 父留言id
     * @param commentMessage 留言内容
     * @param session 会话
     * @return 留言 信息传输类
     */
    CommentDTO replyToComment(int parentCommentId, String commentMessage, HttpSession session) throws LoginInfoException;

    /**
     * 修改留言
     * @param commentId 留言id
     * @param commentMessage 新内容
     * @param session 会话
     * @return 留言 信息传输类
     */
    CommentDTO updateComment(int commentId, String commentMessage, HttpSession session) throws LoginInfoException, UnauthorizedCommentOperationException;

    /**
     * 删除留言
     * @param commentId 留言id
     * @param session 会话
     * @return 是否成功操作
     */
    boolean deleteComment(int commentId, HttpSession session) throws LoginInfoException, UnauthorizedCommentOperationException;
}
