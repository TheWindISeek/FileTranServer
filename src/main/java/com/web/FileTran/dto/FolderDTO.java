package com.web.FileTran.dto;

import com.web.FileTran.dao.usersMapper;
import com.web.FileTran.service.UserService;
import com.web.FileTran.vo.FolderVO;
import com.web.FileTran.vo.ShortcutVO;
import org.springframework.beans.factory.annotation.Autowired;

public class FolderDTO {
    @Autowired
    private static usersMapper usersMapper;
    private Integer id;

    private String name;

    private Integer parentFolderId;

    private String folderType;

    private Integer shortcutDestination;

    private Integer quotaUsed;

    private String permission;

    private Integer inheritedFromFolderId;

    private Integer creatorId;

    public FolderDTO(
            Integer id,
            String name,
            Integer parentFolderId,
            String folderType,
            Integer shortcutDestination,
            Integer quotaUsed,
            String permission,
            Integer inheritedFromFolderId,
            Integer creatorId)
    {
        this.id = id;
        this.name = name;
        this.parentFolderId = parentFolderId;
        this.folderType = folderType;
        this.shortcutDestination = shortcutDestination;
        this.quotaUsed = quotaUsed;
        this.permission = permission;
        this.inheritedFromFolderId = inheritedFromFolderId;
        this.creatorId = creatorId;
    }

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

    public FolderVO convertToFolderVO()
    {
        return new FolderVO(
                this.getId(),
                this.getName(),
                usersMapper.getUserInfoById(this.getCreatorId()).convertToUserDTO().convertToUserVO(),
                this.getFolderType(),
                this.getPermission(),
                ShortcutVO.ShortcutVOAutoCreate_Folder(this.shortcutDestination)
        );
    }
}