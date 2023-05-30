package com.example.api.dao;

import com.example.api.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    /**
     * 用id查询用户信息
     * @param userId 用户id
     * @return pojo对象
     */
    Users selectByPrimaryKey(Integer userId);
    
    /**
     * 用户注册
     * @param U_Name 新用户名字
     * @param U_PASSWORD 新用户密码
     * @return 新用户的id
     */
    int proc_User_Reg(String U_Name,String U_PASSWORD);
    
    /**
     * 用户删除
     * @param U_Id 删除用户的id
     */
    void proc_User_DeReg(int U_Id);
    
    /**
     * 用户重命名
     * @param U_Id 用户id
     * @param U_NewName 用户的新名字
     */
    void proc_User_Rename(int U_Id,String U_NewName);
    
    /**
     * 用户设置密码
     * @param U_Id 用户id
     * @param U_NewPassword 用户的新密码
     */
    void proc_User_SetPassword(int U_Id,String U_NewPassword);
    
    /*底下这几个都用不着*/

    int deleteByPrimaryKey(Integer userId);

    int insert(Users row);

    int insertSelective(Users row);

    int updateByPrimaryKeySelective(Users row);

    int updateByPrimaryKey(Users row);

}