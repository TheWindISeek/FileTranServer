package com.web.FileTran.newdaogenerated;

import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface adminsMapper {
    void registerAdmin(Map<String, Object> params);
    void deleteAdmin(int adminId);
    @MapKey("id")
    Map<String, Object> adminLogin(Map<String, Object> params);
}
