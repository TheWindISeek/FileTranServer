package dao;

import pojo.Tag_Types;

public interface Tag_TypesMapper {
    int deleteByPrimaryKey(Integer tagTypeId);

    int insert(Tag_Types row);

    int insertSelective(Tag_Types row);

    Tag_Types selectByPrimaryKey(Integer tagTypeId);

    int updateByPrimaryKeySelective(Tag_Types row);

    int updateByPrimaryKey(Tag_Types row);
}