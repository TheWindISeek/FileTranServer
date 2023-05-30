package com.example.springboot_demo;

import Random.springboot_demo.config.itheima.mapper.UserMapper;
import Random.springboot_demo.config.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String userEmail = "FAIZ%";
        int userScore = 15;
        //1.获取sql session factory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取sql session 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true自动提交事务

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //手动加%进行模糊查询

        Map map = new HashMap();
        map.put("userEmail", userEmail);
        map.put("userScore", userScore);

        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserScore(15);
        user.setUserRank(15);
        user.setUserName("jin");
        user.setUserId(15);

        mapper.add(user);
//        int update = mapper.update(user);


        List<User> faiz = mapper.selectByCondition(null, 15);
        List<User> mfaiz = mapper.selectByCondition(map);
        List<User> cfaiz = mapper.selectByCondition(new User("", 0, userEmail, userScore, 0));
        List<User> userList = mapper.selectAll();
        User users = mapper.selectById(1);

       // System.out.println(update);
        System.out.println(cfaiz);
        System.out.println(mfaiz);
        System.out.println(faiz);

        System.out.println(users);
        System.out.println(userList);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDeleteById() throws IOException {
        String userEmail = "FAIZ%";
        int userScore = 15;
        //1.获取sql session factory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取sql session 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true自动提交事务

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteById(15);

        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws IOException {
        String userEmail = "FAIZ%";
        int userScore = 15;
        //1.获取sql session factory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取sql session 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true自动提交事务

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int []ids = {11, 15};
        mapper.deleteByIds(ids);

        sqlSession.close();
    }

    @Test
    public void testSelectByMap() throws IOException {
        //1.获取sql session factory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取sql session 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true自动提交事务

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //
        User user = new User();
        user.setUserId(1);

        Map map = new HashMap();
        map.put("user", user);
        List<User> users = mapper.selectByMap(map);
        System.out.println(users);
        sqlSession.close();
    }
}
