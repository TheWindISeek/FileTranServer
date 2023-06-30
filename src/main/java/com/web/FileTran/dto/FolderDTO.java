package com.web.FileTran.dto;

public class FolderDTO {
    private int id;

    private String name;

    private int parentFolderId;

    private String folderType;

    private int shortcutDestination;

    private int quotaUsed;

    private String permission;

    private int inheritedFromFolderId;

    private int creatorId;

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

    public int getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(int parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getFolderType() {
        return folderType;
    }

    public void setFolderType(String folderType) {
        this.folderType = folderType == null ? null : folderType.trim();
    }

    public int getShortcutDestination() {
        return shortcutDestination;
    }

    public void setShortcutDestination(int shortcutDestination) {
        this.shortcutDestination = shortcutDestination;
    }

    public int getQuotaUsed() {
        return quotaUsed;
    }

    public void setQuotaUsed(int quotaUsed) {
        this.quotaUsed = quotaUsed;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
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
}