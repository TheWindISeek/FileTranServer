package com.web.FileTran.dao;

import com.web.FileTran.pojo.po.FilesTagsKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesTagsMapper {
    /*都用不着*/
    int deleteByPrimaryKey(FilesTagsKey key);

    int insert(FilesTagsKey row);

    int insertSelective(FilesTagsKey row);
}