package com.web.FileTran.dao;

import com.web.FileTran.pojo.po.DefaultIcon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DefaultIconMapper {
    /*全都不需要*/
    int deleteByPrimaryKey(Integer id);

    int insert(DefaultIcon row);

    int insertSelective(DefaultIcon row);

    DefaultIcon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DefaultIcon row);

    int updateByPrimaryKeyWithBLOBs(DefaultIcon row);

    int updateByPrimaryKey(DefaultIcon row);
}