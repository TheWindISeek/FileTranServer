package dao;

import pojo.Tags;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tags row);

    int insertSelective(Tags row);

    Tags selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tags row);

    int updateByPrimaryKey(Tags row);
}