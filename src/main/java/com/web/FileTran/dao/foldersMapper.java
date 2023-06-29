package com.web.FileTran.dao;

import com.web.FileTran.pojo.files;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface foldersMapper {
    @MapKey("id")
    Map<String, Object> createFolder(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> renameFolder(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> createFolderShortcut(Map<String, Object> params);
    void deleteFolder(int folderId);
    /*以下查询方法需要service层配合进行查询,如分页前几页固定是文件夹,后几页固定是文件,然后service层确定查找的起始位置和长度*/
    @MapKey("id")
    List<Map<String, Object>> getChildrenFolders(Map<String, Object> params);
    @MapKey("id")
    List<Map<String, Object>> getChildrenFiles(Map<String, Object> params);

    files getFolderInfo(int folderId);
    boolean checkFolderExists(int folderId);
}
