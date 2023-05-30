package com.example.api.dao;


import com.example.api.pojo.Comments;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentsMapper {
	/*根据id得到一个comments对象*/
	/**
	 * 根据id查询留言
	 * @param commentId 要选择的留言的id
	 * @return 查询到的留言,pojo.comments对象
	 */
    Comments selectByPrimaryKey(Integer commentId);
    
    /**
     * 根据id删除留言
     * @param C_ID 要删除的留言id
     */
    void proc_Comment_Delete(int C_ID);
    
    /**
     * 根据id修改留言
     * @param C_ID 要修改的留言id
     * @param C_Msg 留言的新内容
     */
    void proc_Comment_Modify(int C_ID,String C_Msg);
    
    /**
     * 发布一条新的留言
     * @param C_Creator 创建者
     * @param C_Msg 留言内容
     * @param C_Parent 父节点,本条留言是对哪个留言的回复
     * @return 新建留言的id
     */
    int proc_Comment_Publish(int C_Creator,String C_Msg,int C_Parent);
    
    /*其他的都不需要,增删改都有存储过程完成*/

    int deleteByPrimaryKey(Integer commentId);

    int insert(Comments row);

    int insertSelective(Comments row);

    int updateByPrimaryKeySelective(Comments row);

    int updateByPrimaryKeyWithBLOBs(Comments row);

    int updateByPrimaryKey(Comments row);

}