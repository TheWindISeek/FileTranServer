package com.itheima.pojo;

public class User {
   private String user_name;
   private Integer user_id;
   private String user_email;
   private Integer user_score;
   private Integer user_rank;

    public User(String user_name, Integer user_id, String user_email, Integer user_score, Integer user_rank) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_score = user_score;
        this.user_rank = user_rank;
    }

    public String getUser_name() {
        return user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public Integer getUser_score() {
        return user_score;
    }

    public Integer getUser_rank() {
        return user_rank;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_score(Integer user_score) {
        this.user_score = user_score;
    }

    public void setUser_rank(Integer user_rank) {
        this.user_rank = user_rank;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", user_id=" + user_id +
                ", user_email='" + user_email + '\'' +
                ", user_score=" + user_score +
                ", user_rank=" + user_rank +
                '}';
    }
}
