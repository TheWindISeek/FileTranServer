package dao;

import pojo.Administrator;

public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer admId);

    int insert(Administrator row);

    int insertSelective(Administrator row);

    Administrator selectByPrimaryKey(Integer admId);

    int updateByPrimaryKeySelective(Administrator row);

    int updateByPrimaryKey(Administrator row);
}