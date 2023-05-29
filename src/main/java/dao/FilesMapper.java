package dao;

import pojo.Files;

public interface FilesMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(Files row);

    int insertSelective(Files row);

    Files selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(Files row);

    int updateByPrimaryKey(Files row);
}