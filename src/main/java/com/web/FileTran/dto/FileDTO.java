package com.web.FileTran.dto;


import com.web.FileTran.dao.usersMapper;
import com.web.FileTran.vo.FileVO;
import com.web.FileTran.vo.ShortcutVO;
import org.springframework.beans.factory.annotation.Autowired;

public class FileDTO {
    @Autowired
    private static usersMapper usersMapper;
    private Integer id;

    private String name;

    private Integer folderId;

    private String fileType;

    private String permission;

    private Integer shortcutDestination;

    private Integer inheritedFromFolderId;

    private Integer creatorId;

    private Integer blobId;

    public FileDTO(
            Integer id,
            String name,
            Integer folderId,
            String fileType,
            String permission,
            Integer shortcutDestination,
            Integer inheritedFromFolderId,
            Integer creatorId,
            Integer blobId)
    {
        this.id = id;
        this.name = name;
        this.folderId = folderId;
        this.fileType = fileType;
        this.permission = permission;
        this.shortcutDestination = shortcutDestination;
        this.inheritedFromFolderId = inheritedFromFolderId;
        this.creatorId = creatorId;
        this.blobId = blobId;
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

    public FileVO convertToFileVO() {
        return new FileVO(
                this.getId(),
                this.getName(),
                usersMapper.getUserInfoById(this.getCreatorId()).convertToUserDTO().convertToUserVO(),
                this.getFileType(),
                this.getPermission(),
                ShortcutVO.ShortcutVOAutoCreate_File(this.getShortcutDestination()),
                null
        );
    }
}