package com.web.FileTran.vo;


public class FolderVO {
    private long folderId;
    private String folderName;
    private UserVO creator;
    private String folderType;
    private String accessPermission;
    private ShortcutVO shortcut;

    public FolderVO() {
        // Default constructor
    }

    public FolderVO(long folderId, String folderName, UserVO creator, String folderType, String accessPermission, ShortcutVO shortcut) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.creator = creator;
        this.folderType = folderType;
        this.accessPermission = accessPermission;
        this.shortcut = shortcut;
    }

    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public UserVO getCreator() {
        return creator;
    }

    public void setCreator(UserVO creator) {
        this.creator = creator;
    }

    public String getFolderType() {
        return folderType;
    }

    public void setFolderType(String folderType) {
        this.folderType = folderType;
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
}
