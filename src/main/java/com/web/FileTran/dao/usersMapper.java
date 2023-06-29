package com.web.FileTran.dao;

import com.web.FileTran.pojo.users;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface usersMapper {
    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 注册用户
     * @param params 输入 String userName,String userPassword
     * @return 输出 long userId
     */
    @MapKey("id")
    Map<String, Object> registerUser(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 注销用户
     * @param userId 用户id
     */
    void deleteUser(long userId);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 重命名用户
     * @param params 输入 long userId,String newUserName
     * @return success
     */
    boolean renameUser(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 修改用户密码
     * @param params 输入 long userId,String newUserPassword
     * @return success
     */
    boolean changeUserPassword(Map<String, Object> params);

    /**
     * 根据用户名获取用户信息
     * @param username 用户id
     * @return 用户pojo类
     */
    users getUserInfoByUsername(String username);

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户pojo类
     */
    users getUserInfoById(long id);
}
