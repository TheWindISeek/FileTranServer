package com.web.FileTran.dao;

import com.web.FileTran.pojo.po.FilesFilesKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesFilesMapper {
    /*全都不需要*/
    int deleteByPrimaryKey(FilesFilesKey key);

    int insert(FilesFilesKey row);

    int insertSelective(FilesFilesKey row);
}