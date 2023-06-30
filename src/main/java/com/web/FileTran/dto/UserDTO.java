package com.web.FileTran.dto;

import java.sql.Timestamp;

public class UserDTO {
    private int id;

    private String username;

    private String password;

    private Timestamp registrationTimestamp;

    private int userDirectoryId;

    private int favoritesFolderId;

    private int quotaLimit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Timestamp getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(Timestamp registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    public int getUserDirectoryId() {
        return userDirectoryId;
    }

    public void setUserDirectoryId(int userDirectoryId) {
        this.userDirectoryId = userDirectoryId;
    }

    public int getFavoritesFolderId() {
        return favoritesFolderId;
    }

    public void setFavoritesFolderId(int favoritesFolderId) {
        this.favoritesFolderId = favoritesFolderId;
    }

    public int getQuotaLimit() {
        return quotaLimit;
    }

    public void setQuotaLimit(int quotaLimit) {
        this.quotaLimit = quotaLimit;
    }
}