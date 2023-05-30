package com.example.api.pojo;

public class Administrator {
    private Integer admId;//管理员主键

    private String admName;//管理员昵称

    private String admPassword;//管理员密码

    private Integer admPermission;//管理员访问权限

    public Administrator(int admId, String admName, String admPassword, int admPermission) {
        this.admId = admId;
        this.admName = admName;
        this.admPassword = admPassword;
        this.admPermission = admPermission;
    }

    public Integer getAdmId() {
        return admId;
    }

    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName == null ? null : admName.trim();
    }

    public String getAdmPassword() {
        return admPassword;
    }

    public void setAdmPassword(String admPassword) {
        this.admPassword = admPassword == null ? null : admPassword.trim();
    }

    public Integer getAdmPermission() {
        return admPermission;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "admId=" + admId +
                ", admName='" + admName + '\'' +
                ", admPassword='" + admPassword + '\'' +
                ", admPermission=" + admPermission +
                '}';
    }

    public void setAdmPermission(Integer admPermission) {
        this.admPermission = admPermission;
    }
}