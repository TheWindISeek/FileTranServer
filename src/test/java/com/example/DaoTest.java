package com.example;

import com.web.FileTran.dao.*;
import com.web.FileTran.pojo.po.Administrator;

import com.web.FileTran.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author JeffreySharp
 * @apiNote 该类用于测试dao层的接口 每个方法去掉test后再加上mapper 即对应该类下的具体的接口
 */
@SpringBootTest
public class DaoTest {
    /**
     * 测试完毕
     */
    @Test
    public void administratorTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
        //从这里开始调用接口中的方法


        //1. insert()
        Administrator administrator = new Administrator(1, "jeffrey", "jeffrey", 4);
        System.out.println("insert\t" + mapper.insert(administrator));

        //2.insertSelective
        Administrator administrator1 = new Administrator(2, "jzh", "", 0);
        System.out.println("insertSelective\t"+ mapper.insertSelective(administrator1));

        //3.deleteByPrimaryKey
        System.out.println("deleteByPrimaryKey\t" + mapper.deleteByPrimaryKey(2));

        //4.selectByPrimaryKey
        administrator1 = mapper.selectByPrimaryKey(1);
        System.out.println("selectByPrimaryKey\t" + administrator1.toString());

        //5.updateByPrimaryKeySelective
        administrator1.setAdmPassword("");
        administrator1.setAdmName("");
        System.out.println("updateByPrimaryKeySelective\t"+ mapper.updateByPrimaryKey(administrator1));
        System.out.println("selectByPrimaryKey\t" + mapper.selectByPrimaryKey(administrator1.getAdmId()));

        //6.updateByPrimaryKey
        administrator1.setAdmName("jzh");
        administrator1.setAdmPassword("jzh");
        System.out.println("updateByPrimaryKey\t" + mapper.updateByPrimaryKey(administrator1));
        System.out.println("selectByPrimaryKey\t" + mapper.selectByPrimaryKey(administrator1.getAdmId()));



        //这里结束调用
        sqlSession.commit();//提交事务
        sqlSession.close();//释放连接
    }

    /**
     * 肯定得测
     */
    @Test
    public void blobsTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        BlobsMapper mapper = sqlSession.getMapper(BlobsMapper.class);


        //从这里开始调用接口中的方法



        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 最好测
     */
    @Test
    public void blobUsageTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        BlobUsageMapper mapper = sqlSession.getMapper(BlobUsageMapper.class);


        //从这里开始调用接口中的方法

        //这里结束调用

        //提交并释放资源
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试过 但是需要测试
     */
    @Test
    public void commentsTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        CommentsMapper mapper = sqlSession.getMapper(CommentsMapper.class);

        //从这里开始调用接口中的方法


        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 服务器自动完成 不需要测试
     */
    @Test
    public void filesFilesTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        FilesFilesMapper mapper = sqlSession.getMapper(FilesFilesMapper.class);

        //从这里开始调用接口中的方法


        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 不保证完全正确 需要测试
     */
    @Test
    public void filesTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        FilesMapper mapper = sqlSession.getMapper(FilesMapper.class);

        //从这里开始调用接口中的方法


        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 没写
     */
    @Test
    public void filesTagsTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        FilesTagsMapper mapper = sqlSession.getMapper(FilesTagsMapper.class);

        //从这里开始调用接口中的方法


        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 没写
     */
    @Test
    public void tagsTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        TagsMapper mapper = sqlSession.getMapper(TagsMapper.class);

        //从这里开始调用接口中的方法


        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 没写
     */
    @Test
    public void tagTypesTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        TagTypesMapper mapper = sqlSession.getMapper(TagTypesMapper.class);

        //从这里开始调用接口中的方法


        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 服务器测试完毕
     */
    @Test
    public void usersTest() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//不自动开启事务 写操作就不会被写入到数据库中
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);

        //从这里开始调用接口中的方法


        //这里结束调用
        sqlSession.commit();
        sqlSession.close();
    }
}
