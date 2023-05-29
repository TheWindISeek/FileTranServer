package dao;

import pojo.Default_Icon;

public interface Default_IconMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Default_Icon row);

    int insertSelective(Default_Icon row);

    Default_Icon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Default_Icon row);

    int updateByPrimaryKeyWithBLOBs(Default_Icon row);

    int updateByPrimaryKey(Default_Icon row);
}