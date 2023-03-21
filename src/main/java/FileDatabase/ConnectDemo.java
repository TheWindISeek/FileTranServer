package FileDatabase;

import java.sql.*;

public class ConnectDemo {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/filetrandatabase?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "!Lucifer031337";

    public void searchUser() {

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "select * from user";
            ResultSet rs = statement.executeQuery(sql);
            if(rs == null) {
                System.out.println("error");
            } else {
                while (rs.next()) {
                    System.out.println(rs.getString("user_name"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ConnectDemo().searchUser();
    }
}
