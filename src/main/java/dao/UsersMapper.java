package dao;

import pojo.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Users row);

    int insertSelective(Users row);

    Users selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Users row);

    int updateByPrimaryKey(Users row);
}