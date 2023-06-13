package com.web.FileTran.dao;

import com.web.FileTran.pojo.po.BlobUsageKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlobUsageMapper {
    /*全都不需要,不用注释*/
    int deleteByPrimaryKey(BlobUsageKey key);

    int insert(BlobUsageKey row);

    int insertSelective(BlobUsageKey row);
}