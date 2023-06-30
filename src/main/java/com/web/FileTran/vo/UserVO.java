package com.web.FileTran.vo;

public class UserVO {
    private int userId;
    private String username;
    private int userDirectoryId;
    private int favorites;

    public UserVO() {
        // Default constructor
    }

    public UserVO(int userId, String username, int userDirectoryId, int favorites) {
        this.userId = userId;
        this.username = username;
        this.userDirectoryId = userDirectoryId;
        this.favorites = favorites;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserDirectoryId() {
        return userDirectoryId;
    }

    public void setUserDirectoryId(int userDirectoryId) {
        this.userDirectoryId = userDirectoryId;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }
}
