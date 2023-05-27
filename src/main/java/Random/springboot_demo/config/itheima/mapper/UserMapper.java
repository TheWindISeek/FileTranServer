package Random.springboot_demo.config.itheima.mapper;

import Random.springboot_demo.config.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 查询所有
     * */
    List<User> selectAll();
    /**
     * 根据给定id查询
     * */
    User selectById(int id);
    /**
     * 根据给定的用户条件查询
     * */
    List<User> selectByCondition(@Param("userEmail")String userEmail, @Param("userScore")int userScore);
    /**
     * 根据给定的User信息查询
     * */
    List<User> selectByCondition(User user);
    /**
     * 根据给定的映射查询
     * */
    List<User> selectByCondition(Map map);

    List<User> selectByMap(Map map);
    /**
     * 添加指定的user用户
     * */
    void add(User user);
    /**
     * 更新user信息为输入 给定的user id应当存在
     * */
    int update(User user);
    /**
     * 删除指定的用户ID
     * */
    void deleteById(int userId);
    /**
     * 删除给定的用户组
     * */
    void deleteByIds(int[] userIds);
}
