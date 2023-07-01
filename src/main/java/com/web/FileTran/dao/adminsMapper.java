package com.web.FileTran.dao;

import com.web.FileTran.pojo.admins;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface adminsMapper {
    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 注册管理员
     * @param params 参数 输入:String adminName,String adminPassword,输出 int adminId
     */
    void registerAdmin(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 注销管理员
     * @param params 管理员id 输入:int adminId
     */
    void deleteAdmin(Map<String, Object> params);

    /**
     * 根据用户名查找管理员
     * @param username 用户名
     * @return 管理员pojo
     */
    admins getAdminInfoByUsername(String username);
}
