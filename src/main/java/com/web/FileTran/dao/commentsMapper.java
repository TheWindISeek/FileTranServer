package com.web.FileTran.dao;

import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface commentsMapper {
    @MapKey("id")
    Map<String, Object> addCommentToFile(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> addCommentToFolder(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> replyToComment(Map<String, Object> params);
    void updateComment(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> deleteComment(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> getCommentInfo(int commentId);
}