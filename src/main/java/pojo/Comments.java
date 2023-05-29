package pojo;

import java.util.Date;

public class Comments {
    private Integer commentId;

    private Integer commentType;

    private Integer commentCreator;

    private Date commentUpd;

    private Date commentMod;

    private Integer commentParent;

    private String commentMsg;

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