package com.web.FileTran.pojo;

import java.util.Date;

public class users {
    private Integer id;

    private String username;

    private String password;

    private Date registrationDate;

    private Integer userDirectoryId;

    private Integer favoritesFolderId;

    private Integer quotaLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getUserDirectoryId() {
        return userDirectoryId;
    }

    public void setUserDirectoryId(Integer userDirectoryId) {
        this.userDirectoryId = userDirectoryId;
    }

    public Integer getFavoritesFolderId() {
        return favoritesFolderId;
    }

    public void setFavoritesFolderId(Integer favoritesFolderId) {
        this.favoritesFolderId = favoritesFolderId;
    }

    public Integer getQuotaLimit() {
        return quotaLimit;
    }

    public void setQuotaLimit(Integer quotaLimit) {
        this.quotaLimit = quotaLimit;
    }
}