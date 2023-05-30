package com.example.api.pojo;

import java.util.HashMap;
import java.util.Map;

public class Users {
    private Integer userId;//普通用户的id

    private String userName;//普通用户的昵称

    private String userPassword;//普通用户的密码

    private Integer userFilelist;//普通用户的文件列表

    private Integer userFavorites;//普通用户的收藏文件的id

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFilelist=" + userFilelist +
                ", userFavorites=" + userFavorites +
                '}';
    }

    public Users(Integer userId, String userName, String userPassword, Integer userFilelist, Integer userFavorites) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFilelist = userFilelist;
        this.userFavorites = userFavorites;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserFilelist() {
        return userFilelist;
    }

    public void setUserFilelist(Integer userFilelist) {
        this.userFilelist = userFilelist;
    }

    public Integer getUserFavorites() {
        return userFavorites;
    }

    public void setUserFavorites(Integer userFavorites) {
        this.userFavorites = userFavorites;
    }
    /*以下是对应mapper存储过程的方法*/
    public int User_Reg(String U_Name,String U_PASSWORD) {
    	Map<String,Object> map=new HashMap<String,Object>();  
    	map.put("U_Name", U_Name);  
    	map.put("String", U_PASSWORD);  
    	int NEW_UID = (int) map.get("NEW_UID");
    	System.out.println(NEW_UID); 
    	return NEW_UID;
    }
/*
@Test
public void procSearchUser()
{
    DataConnection dataConnection = new DataConnection();
    SqlSession sqlSession = dataConnection.getSqlSession();
    //封装查询参数
    Map params = new HashMap();
    params.put("page_index",2);  //输入参数：当前页码
    params.put("page_size",10);  //输入参数：分页大小
    params.put("total_count",0); //输出参数：数据总数
    params.put("total_page",0);  //输出参数：总页数
    //调用存储过程
    List<User> userList = sqlSession.selectList("test.proc_search_user",params);
    System.out.println("查询第"+ params.get("page_index") +"页的数据，每页共"+params.get("page_size")+"条数据");
    //遍历用户列表
    for (User user : userList)
    {
        System.out.println("编号：" + user.getId() +" 姓名：" + user.getUserName() + " 性别：" + user.getSex());
    }
    //获取输出参数
    System.out.println("数据总数：" + params.get("total_count"));
    System.out.println("总页数：" + params.get("total_page"));
    sqlSession.close();
    */
}
