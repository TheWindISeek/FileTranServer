package com.web.FileTran.vo;


import java.util.List;

public class FileVO {
    private long fileId;
    private String fileName;
    private UserVO creator;
    private String fileType;
    private String accessPermission;
    private ShortcutVO shortcut;
    private List<CommentVO> comments;

    public FileVO() {
        // Default constructor
    }

    public FileVO(long fileId, String fileName, UserVO creator, String fileType, String accessPermission, ShortcutVO shortcut, List<CommentVO> comments) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.creator = creator;
        this.fileType = fileType;
        this.accessPermission = accessPermission;
        this.shortcut = shortcut;
        this.comments = comments;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UserVO getCreator() {
        return creator;
    }

    public void setCreator(UserVO creator) {
        this.creator = creator;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(String accessPermission) {
        this.accessPermission = accessPermission;
    }

    public ShortcutVO getShortcut() {
        return shortcut;
    }

    public void setShortcut(ShortcutVO shortcut) {
        this.shortcut = shortcut;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }
}
