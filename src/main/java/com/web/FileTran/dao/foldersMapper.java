package com.web.FileTran.dao;

import com.web.FileTran.pojo.folders;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface foldersMapper {
    // TODO 需要修改存储过程添加文件夹名参数
    /**
     * 创建文件夹
     * @param params 输入 long parentFolderId,long creatorId
     * @return 输出 long newFolderId
     */
    @MapKey("id")
    Map<String, Object> createFolder(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 重命名文件夹
     * @param params 输入 folderId,newFolderName
     * @return 重命名是否成功
     */
    boolean renameFolder(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 创建快捷方式
     * @param params 输入 long parentFolderId,String shortcutName,long creatorId,long targetFolderId
     * @return 输出 long newShortcutId
     */
    @MapKey("id")
    Map<String, Object> createFolderShortcut(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 删除文件夹
     * @param folderId 文件夹id
     */
    void deleteFolder(long folderId);

    /* TODO 以下查询方法需要service层配合进行查询,如分页前几页固定是文件夹,后几页固定是文件,然后service层确定查找的起始位置和长度*/
    /**
     * 查询子文件夹 注:需要service层控制查找的起点和长度
     * @param params 输入 long folderId,long offset,long limit
     * @return list
     */
    @MapKey("id")
    List<Map<String, Object>> getChildrenFolders(Map<String, Object> params);

    /**
     * 查询子文件 注:需要service层控制查找的起点和长度
     * @param params 输入 long fileId,long offset,long limit
     * @return list
     */
    @MapKey("id")
    List<Map<String, Object>> getChildrenFiles(Map<String, Object> params);

    /**
     * 获得文件夹信息
     * @param folderId 文件夹id
     * @return 文件夹pojo
     */
    folders getFolderInfo(long folderId);

    /**
     * 查询文件夹是否存在
     * @param folderId 文件夹id
     * @return 文件夹是否存在
     */
    boolean checkFolderExists(long folderId);
}
