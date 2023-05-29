package dao;

import pojo.Blob_UsageKey;

public interface Blob_UsageMapper {
    int deleteByPrimaryKey(Blob_UsageKey key);

    int insert(Blob_UsageKey row);

    int insertSelective(Blob_UsageKey row);
}