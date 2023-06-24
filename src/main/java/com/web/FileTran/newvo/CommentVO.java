package com.web.FileTran.newvo;


import java.time.LocalDateTime;

public class CommentVO {
    private long commentId;
    private String message;
    private LocalDateTime postedAt;
    private LocalDateTime lastReplyAt;

    public CommentVO() {
        // Default constructor
    }

    public CommentVO(long commentId, String message, LocalDateTime postedAt, LocalDateTime lastReplyAt) {
        this.commentId = commentId;
        this.message = message;
        this.postedAt = postedAt;
        this.lastReplyAt = lastReplyAt;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
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
