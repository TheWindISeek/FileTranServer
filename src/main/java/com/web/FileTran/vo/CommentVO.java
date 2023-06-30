package com.web.FileTran.vo;


import java.time.LocalDateTime;

public class CommentVO {
    private int commentId;
    private String message;
    private LocalDateTime postedAt;
    private LocalDateTime lastReplyAt;

    public CommentVO() {
        // Default constructor
    }

    public CommentVO(int commentId, String message, LocalDateTime postedAt, LocalDateTime lastReplyAt) {
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

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public LocalDateTime getLastReplyAt() {
        return lastReplyAt;
    }

    public void setLastReplyAt(LocalDateTime lastReplyAt) {
        this.lastReplyAt = lastReplyAt;
    }
}
