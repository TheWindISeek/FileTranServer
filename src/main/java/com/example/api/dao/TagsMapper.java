package com.example.api.dao;


import com.example.api.pojo.Tags;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagsMapper {
	/*tag相关的暂时不做*/

    int deleteByPrimaryKey(Integer tagId);

    int insert(Tags row);

    int insertSelective(Tags row);

    Tags selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tags row);

    int updateByPrimaryKey(Tags row);

}