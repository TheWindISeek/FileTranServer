package com.web.FileTran.dao;

import com.web.FileTran.pojo.files;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface filesMapper {
    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 创建(上传)文件
     * @param params 输入 long parentFolderId,long creatorId,String fileName,Blob fileContent
     * @return 输出 long newFileId,String errorMessage
     */
    @MapKey("id")
    Map<String, Object> createFile(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 创建快捷方式
     * @param params 输入 long parentFolderId,long creatorId,String fileName,long targetFileId
     * @return 输出 long newShortcutId,String errorMessage
     */
    @MapKey("id")
    Map<String, Object> createFileShortcut(Map<String, Object> params);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 删除文件
     * @param fileId 待删除文件id
     */
    void deleteFile(long fileId);

    // TODO 需要修改存储过程,添加错误信息输出
    /**
     * 下载文件
     * @param fileId 文件id
     * @return 输出 Blob fileBlob,String errorMessage
     */
    @MapKey("id")
    Map<String, Object> downloadFile(long fileId);

    /**
     * 获得文件信息
     * @param fileId 文件id
     * @return pojo类
     */
    @MapKey("id")
    files getFileInfo(long fileId);

    /**
     * 查询文件是否存在
     * @param fileId 文件id
     * @return 文件是否存在
     */
    boolean checkFileExists(long fileId);
}
