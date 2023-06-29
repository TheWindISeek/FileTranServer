package com.web.FileTran.dto;


public class FileDTO {
    private Integer id;

    private String name;

    private Integer folderId;

    private String fileType;

    private String permission;

    private Integer shortcutDestination;

    private Integer inheritedFromFolderId;

    private Integer creatorId;

    private Integer blobId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
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

    public Integer getShortcutDestination() {
        return shortcutDestination;
    }

    public void setShortcutDestination(Integer shortcutDestination) {
        this.shortcutDestination = shortcutDestination;
    }

    public Integer getInheritedFromFolderId() {
        return inheritedFromFolderId;
    }

    public void setInheritedFromFolderId(Integer inheritedFromFolderId) {
        this.inheritedFromFolderId = inheritedFromFolderId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getBlobId() {
        return blobId;
    }

    public void setBlobId(Integer blobId) {
        this.blobId = blobId;
    }
}