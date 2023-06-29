package com.web.FileTran.dto;

import java.util.Date;

public class CommentDTO {
    private Integer id;

    private Integer fileId;

    private Integer parentCommentId;

    private Integer userId;

    private Date postedAt;

    private Date lastReplyAt;

    private String message;

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

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public Date getLastReplyAt() {
        return lastReplyAt;
    }

    public void setLastReplyAt(Date lastReplyAt) {
        this.lastReplyAt = lastReplyAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}