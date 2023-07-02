package com.web.FileTran.pojo;

import com.web.FileTran.dto.FolderDTO;

public class folders {
    private Integer id;

    private String name;

    private Integer parentFolderId;

    private String folderType;

    private Integer shortcutDestination;

    private Integer quotaUsed;

    private String permission;

    private Integer inheritedFromFolderId;

    private Integer creatorId;

    public String toString() {
        return "folders{"+
                " id="+id+
                "; name="+name+
                "; parentFolderId="+parentFolderId+
                "; folderType="+folderType+
                "; shortcutDestination="+shortcutDestination+
                "; quotaUsed="+quotaUsed+
                "; permission="+permission+
                "; inheritedFromFolderId="+inheritedFromFolderId+
                "; creatorId="+creatorId+"}";
    };

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

    public Integer getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(Integer parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getFolderType() {
        return folderType;
    }

    public void setFolderType(String folderType) {
        this.folderType = folderType == null ? null : folderType.trim();
    }

    public Integer getShortcutDestination() {
        return shortcutDestination;
    }

    public void setShortcutDestination(Integer shortcutDestination) {
        this.shortcutDestination = shortcutDestination;
    }

    public Integer getQuotaUsed() {
        return quotaUsed;
    }

    public void setQuotaUsed(Integer quotaUsed) {
        this.quotaUsed = quotaUsed;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
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

    public FolderDTO convertToFolderDTO()
    {
        return new FolderDTO(
                this.getId(),
                this.getName(),
                this.getParentFolderId(),
                this.getFolderType(),
                this.getShortcutDestination(),
                this.getQuotaUsed(),
                this.getPermission(),
                this.getInheritedFromFolderId(),
                this.getCreatorId()
        );
    }
}