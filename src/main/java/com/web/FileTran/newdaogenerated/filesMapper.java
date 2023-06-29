package com.web.FileTran.newdaogenerated;

import com.web.FileTran.newpojo.files;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface filesMapper {
    @MapKey("id")
    Map<String, Object> createFile(Map<String, Object> params);
    @MapKey("id")
    Map<String, Object> createFileShortcut(Map<String, Object> params);
    void deleteFile(int fileId);
    @MapKey("id")
    Map<String, Object> downloadFile(int fileId);
    @MapKey("id")
    Map<String, Object> getFileInfo(int fileId);
    boolean checkFileExists(long fileId);
}
