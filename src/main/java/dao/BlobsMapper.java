package dao;

import pojo.Blobs;

public interface BlobsMapper {
    int deleteByPrimaryKey(Integer blobId);

    int insert(Blobs row);

    int insertSelective(Blobs row);

    Blobs selectByPrimaryKey(Integer blobId);

    int updateByPrimaryKeySelective(Blobs row);

    int updateByPrimaryKeyWithBLOBs(Blobs row);

    int updateByPrimaryKey(Blobs row);
}