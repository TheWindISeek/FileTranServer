package Random.springboot_demo.config.itheima.pojo;

public class User {
   private String userName;
   private Integer userId;
   private String userEmail;
   private Integer userScore;
   private Integer userRank;

   public User() {}

    public User(String userName, Integer userId, String user_email, Integer user_score, Integer user_rank) {
        this.userName = userName;
        this.userId = userId;
        this.userEmail = user_email;
        this.userScore = user_score;
        this.userRank = user_rank;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userScore=" + userScore +
                ", userRank=" + userRank +
                '}';
    }
}
