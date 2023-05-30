package com.example.api.dao;

import com.example.api.pojo.BlobUsageKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlobUsageMapper {
	/*全都不需要,不用注释*/
    int deleteByPrimaryKey(BlobUsageKey key);

    int insert(BlobUsageKey row);

    int insertSelective(BlobUsageKey row);
}