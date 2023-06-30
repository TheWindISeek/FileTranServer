package com.web.FileTran.dto;


public class FileDTO {
    private int id;

    private String name;

    private int folderId;

    private String fileType;

    private String permission;

    private int shortcutDestination;

    private int inheritedFromFolderId;

    private int creatorId;

    private int blobId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public int getShortcutDestination() {
        return shortcutDestination;
    }

    public void setShortcutDestination(int shortcutDestination) {
        this.shortcutDestination = shortcutDestination;
    }

    public int getInheritedFromFolderId() {
        return inheritedFromFolderId;
    }

    public void setInheritedFromFolderId(int inheritedFromFolderId) {
        this.inheritedFromFolderId = inheritedFromFolderId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getBlobId() {
        return blobId;
    }

    public void setBlobId(int blobId) {
        this.blobId = blobId;
    }
}