package dao;

import pojo.Files_FilesKey;

public interface Files_FilesMapper {
    int deleteByPrimaryKey(Files_FilesKey key);

    int insert(Files_FilesKey row);

    int insertSelective(Files_FilesKey row);
}