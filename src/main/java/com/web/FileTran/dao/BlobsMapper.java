package com.web.FileTran.dao;

import com.web.FileTran.pojo.po.Blobs;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BlobsMapper {
    /*不需要,上传下载提供了存储过程的接口,这里不需要用到*/
    /*全都不需要,不用动*/

    int deleteByPrimaryKey(Integer blobId);

    int insert(Blobs row);

    int insertSelective(Blobs row);

    Blobs selectByPrimaryKey(Integer blobId);

    int updateByPrimaryKeySelective(Blobs row);

    int updateByPrimaryKeyWithBLOBs(Blobs row);

    int updateByPrimaryKey(Blobs row);

}