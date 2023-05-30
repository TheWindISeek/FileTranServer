package com.example.api.dao;

import com.example.api.pojo.TagTypes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagTypesMapper {
	/*都用不着*/
    int deleteByPrimaryKey(Integer tagTypeId);

    int insert(TagTypes row);

    int insertSelective(TagTypes row);

    TagTypes selectByPrimaryKey(Integer tagTypeId);

    int updateByPrimaryKeySelective(TagTypes row);

    int updateByPrimaryKey(TagTypes row);
}