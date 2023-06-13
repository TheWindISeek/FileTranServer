package com.web.FileTran.dao;


import com.web.FileTran.pojo.po.Administrator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdministratorMapper {
    /***
     * 删除主键对应的管理员
     * @param admId 要删除的管理员的主键
     * @return 删除结果
     */
    int deleteByPrimaryKey(Integer admId);

    /**
     * 插入新的管理员对象
     *
     * @param row 管理员对象
     * @return 新创建管理员的admId
     */
    int insert(Administrator row);

    /**
     * 动态sql插入
     *
     * @param row 要插入的对象
     * @return 新创建管理员的admId
     */
    int insertSelective(Administrator row);

    /**
     * 根据主键返回管理员
     *
     * @param admId 管理员id
     * @return 管理员对象
     */
    Administrator selectByPrimaryKey(Integer admId);

    /**
     * 利用动态sql 更新管理员
     *
     * @param row 管理员对象
     * @return 更新完返回的主键?
     */
    int updateByPrimaryKeySelective(Administrator row);

    /**
     * 更新管理员
     *
     * @param row 管理员对象
     * @return 更新完返回的主键
     */
    int updateByPrimaryKey(Administrator row);
}