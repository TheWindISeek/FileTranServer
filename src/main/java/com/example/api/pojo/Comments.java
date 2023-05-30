package com.example.api.pojo;

import java.util.Date;

public class Comments {
    private Integer commentId;//评论id

    private Integer commentType;//评论类型 what

    private Integer commentCreator;//评论创建者id

    private Date commentUpd;//评论更新时间

    private Date commentMod;//评论 创建时间

    public Comments() {
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentId=" + commentId +
                ", commentType=" + commentType +
                ", commentCreator=" + commentCreator +
                ", commentUpd=" + commentUpd +
                ", commentMod=" + commentMod +
                ", commentParent=" + commentParent +
                ", commentMsg='" + commentMsg + '\'' +
                '}';
    }

    private Integer commentParent;//上一级评论

    private String commentMsg;//评论的具体内容

    public Comments(int commentId, int commentType, int commentCreator, Date commentUpd, Date commentMod, int commentParent, String commentMsg) {
        this.commentId = commentId;
        this.commentType = commentType;
        this.commentCreator = commentCreator;
        this.commentUpd = commentUpd;
        this.commentMod = commentMod;
        this.commentParent = commentParent;
        this.commentMsg = commentMsg;
    }
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Integer getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(Integer commentCreator) {
        this.commentCreator = commentCreator;
    }

    public Date getCommentUpd() {
        return commentUpd;
    }

    public void setCommentUpd(Date commentUpd) {
        this.commentUpd = commentUpd;
    }

    public Date getCommentMod() {
        return commentMod;
    }

    public void setCommentMod(Date commentMod) {
        this.commentMod = commentMod;
    }

    public Integer getCommentParent() {
        return commentParent;
    }

    public void setCommentParent(Integer commentParent) {
        this.commentParent = commentParent;
    }

    public String getCommentMsg() {
        return commentMsg;
    }

    public void setCommentMsg(String commentMsg) {
        this.commentMsg = commentMsg == null ? null : commentMsg.trim();
    }
}