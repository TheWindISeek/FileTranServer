package com.web.FileTran.vo;


public class ShortcutVO {
    private int targetId;
    private String targetName;
    private UserVO creator;

    public ShortcutVO() {
        // Default constructor
    }

    public ShortcutVO(int targetId, String targetName, UserVO creator) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.creator = creator;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
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
