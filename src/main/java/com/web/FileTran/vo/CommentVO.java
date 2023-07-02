package com.web.FileTran.vo;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentVO {
    private int commentId;
    private String message;
    private Timestamp postedAt;
    private Timestamp lastReplyAt;

    public CommentVO() {
        // Default constructor
    }

    public CommentVO(int commentId, String message, Timestamp postedAt, Timestamp lastReplyAt) {
        this.commentId = commentId;
        this.message = message;
        this.postedAt = postedAt;
        this.lastReplyAt = lastReplyAt;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
