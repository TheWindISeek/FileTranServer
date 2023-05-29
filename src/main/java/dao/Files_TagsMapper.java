package dao;

import pojo.Files_TagsKey;

public interface Files_TagsMapper {
    int deleteByPrimaryKey(Files_TagsKey key);

    int insert(Files_TagsKey row);

    int insertSelective(Files_TagsKey row);
}