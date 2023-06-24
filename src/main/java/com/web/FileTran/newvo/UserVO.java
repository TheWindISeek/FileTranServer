package com.web.FileTran.newvo;

public class UserVO {
    private long userId;
    private String username;
    private long userDirectoryId;
    private long favorites;

    public UserVO() {
        // Default constructor
    }

    public UserVO(long userId, String username, long userDirectoryId, long favorites) {
        this.userId = userId;
        this.username = username;
        this.userDirectoryId = userDirectoryId;
        this.favorites = favorites;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserDirectoryId() {
        return userDirectoryId;
    }

    public void setUserDirectoryId(long userDirectoryId) {
        this.userDirectoryId = userDirectoryId;
    }

    public long getFavorites() {
        return favorites;
    }

    public void setFavorites(long favorites) {
        this.favorites = favorites;
    }
}
