package com.web.FileTran.dao;

import com.web.FileTran.pojo.comments;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface commentsMapper {
    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 向文件添加回复
     * @param params 参数 输入:int fileId,int userId,String commentMessage
     * @return 输出 int commentId,String errorMessage
     */
    @MapKey("id")
    Map<String, Object> addCommentToFile(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 向文件夹添加回复
     * @param params 参数 输入:int folderId,int userId,String commentMessage
     * @return 输出 int commentId,String errorMessage
     */
    @MapKey("id")
    Map<String, Object> addCommentToFolder(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 对某条留言进行回复
     * @param params 参数 输入:int parentCommentId,int userId,String commentMessage
     * @return 输出 int commentId,String errorMessage
     */
    @MapKey("id")
    Map<String, Object> replyToComment(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 修改某条留言
     * @param params 参数 int commentId,String commentMessage
     * @return 输出 String errorMessage
     */
    @MapKey("id")
    Map<String, Object> updateComment(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 删除某条留言
     * @param params 参数 int commentId
     * @return 输出 String errorMessage
     */
    @MapKey("id")
    Map<String, Object> deleteComment(Map<String, Object> params);

    /**
     * 获得某条留言的信息
     * @param commentId 留言id
     * @return 留言pojo类
     */
    comments getCommentInfo(int commentId);
}