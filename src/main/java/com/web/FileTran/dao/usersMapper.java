package com.web.FileTran.dao;

import com.web.FileTran.pojo.users;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface usersMapper {
    void registerUser(Map<String, Object> params);
    void deleteUser(int userId);
    @MapKey("id")
    Map<String, Object> renameUser(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> changeUserPassword(Map<String, Object> params);

    users getUserInfoByUsername(String username);

    users getUserInfoById(int id);
}
