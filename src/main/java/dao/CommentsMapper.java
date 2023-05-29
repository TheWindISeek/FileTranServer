package dao;

import pojo.Comments;

public interface CommentsMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comments row);

    int insertSelective(Comments row);

    Comments selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comments row);

    int updateByPrimaryKeyWithBLOBs(Comments row);

    int updateByPrimaryKey(Comments row);
}