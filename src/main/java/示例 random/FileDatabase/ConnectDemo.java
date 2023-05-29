package Random.FileDatabase;

import Random.springboot_demo.config.itheima.mapper.UserMapper;
import Random.springboot_demo.config.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

/*
* JDBC快速入门
* */
public class ConnectDemo {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/filetrandatabase?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "!Lucifer031337";

    public void searchUser() {

        try {

            //Class.forName(driver);
            Class.forName("com.mysql.cj.jdbc.Driver");
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
            //关闭连接
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
//        new ConnectDemo().searchUser();
        //加载mybatis的核心配置文件 获取sqlSelSession


        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlsession对象 用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /* 方法一
        //执行sql
        List<User> users = sqlSession.selectList("test.selectAll");
        System.out.println(users);
        */

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
        //关闭资源
        sqlSession.close();
    }
}
