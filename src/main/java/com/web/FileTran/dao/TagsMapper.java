package com.web.FileTran.dao;


import com.web.FileTran.pojo.po.Tags;
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