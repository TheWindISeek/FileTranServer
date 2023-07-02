package com.web.FileTran.dto;

import com.web.FileTran.vo.CommentVO;

import java.sql.Timestamp;

public class CommentDTO {
    private Integer id;

    private Integer fileId;

    private Integer parentCommentId;

    private Integer userId;

    private Timestamp postedAt;

    private Timestamp lastReplyAt;

    private String message;
    public CommentDTO(Integer id,Integer fileId,Integer parentCommentId,Integer userId,Timestamp postedAt,Timestamp lastReplyAt,String message)
    {
        this.id = id;
        this.fileId = fileId;
        this.parentCommentId = parentCommentId;
        this.userId = userId;
        this.postedAt = postedAt;
        this.lastReplyAt = lastReplyAt;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Timestamp postedAt) {
        this.postedAt = postedAt;
    }

    public Timestamp getLastReplyAt() {
        return lastReplyAt;
    }

    public void setLastReplyAt(Timestamp lastReplyAt) {
        this.lastReplyAt = lastReplyAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public CommentVO convertToCommentVO()
    {
        return new CommentVO(
                this.getId(),
                this.getMessage(),
                this.getPostedAt(),
                this.getLastReplyAt()
        );
    }
}