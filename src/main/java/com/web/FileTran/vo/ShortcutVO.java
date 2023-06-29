package com.web.FileTran.vo;


public class ShortcutVO {
    private long targetId;
    private String targetName;
    private UserVO creator;

    public ShortcutVO() {
        // Default constructor
    }

    public ShortcutVO(long targetId, String targetName, UserVO creator) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.creator = creator;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public UserVO getCreator() {
        return creator;
    }

    public void setCreator(UserVO creator) {
        this.creator = creator;
    }
}
