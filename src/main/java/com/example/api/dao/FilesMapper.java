package com.example.api.dao;

import com.example.api.pojo.Files;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesMapper {
    Files selectByPrimaryKey(Integer fileId);
    
    
    /**
     * 文件复制
     * @param F_Path 文件路径,复制出来的文件放到哪个文件夹下
     * @param F_Source 源文件,被复制的文件
     * @param F_CREATOR 创建者,复制出来的新文件被谁创建
     * @return 复制出的新文件的id
     */
    int proc_File_Copy(int F_Path,int F_Source,int F_CREATOR);
    
    /**
     * 文件夹创建
     * @param F_NAME 新文件名字
     * @param F_CREATOR 文件创建者
     * @param F_Path 文件路径,新建文件夹放在哪个文件夹下
     * @return 新建文件的id
     */
    int proc_File_CreateFolder(String F_NAME,int F_CREATOR,int F_Path);
    
    /**
     * 文件强制删除 删掉文件A,假如文件夹B和C都包含A,那么B和C中都找不到A了
     * @param F_Id 文件id A
     */
    int proc_File_Delete(int F_Id);
    
    /**
     * 文件解除引用 从文件夹B删掉文件A,假如文件夹C也包含A,那么C不会受影响
     * @param F_Id 文件id A
     * @param F_Path 文件夹id B
     */
    int proc_File_Dereference(int F_Id,int F_Path);
    
    /**
     * 文件下载接口
     * @param F_ID 文件id
     * @return 文件内容,原本是BLOB,具体能否直接用byte[]上传不确定
     */
    byte[] proc_File_Download(int F_ID);
    
    /**
     * 获得文件图标
     * @param F_ID 文件id
     * @return 图标,如果没有自定义图标,会得到默认图标,原本是BLOB,具体能否直接用byte[]上传不确定
     */
    byte[] proc_File_GetIcon(int F_ID);
    
    /**
     * 文件移动 把文件C从文件夹A移动到文件夹B
     * @param F_MoveFrom 原文件夹A
     * @param F_MoveTo 目标文件夹B
     * @param F_Source 被移动的文件C
     */
    void proc_File_Move(int F_MoveFrom,int F_MoveTo,int F_Source);
    
    /**
     * 文件覆盖
     * @param F_Id 文件id
     * @param File_Data 文件内容
     */
    void proc_File_Overwrite(int F_Id,byte[] File_Data);
    
    /**
     * 文件重命名
     * @param F_Id 文件id
     * @param F_NewName 文件新名字,原本是BLOB,具体能否直接用byte[]上传不确定
     */
    void proc_File_Rename(int F_Id,String F_NewName);
    
    /**
     * 创建文件引用
     * @param F_Path 文件路径,引用到哪个文件夹下
     * @param F_Source 源文件,被引用的文件
     */
    void proc_File_Reference(int F_Path,int F_Source);
    
    /**
     * 文件设置图标
     * @param F_Id 文件id
     * @param F_Icon 默认图标id
     * @param F_Icon_Custom 自定义图标,原本是BLOB,具体能否直接用byte[]上传不确定 可以为null
     */
    void proc_File_SetIcon(int F_Id,int F_Icon,byte[] F_Icon_Custom);
    
    /**
     * 文件上传接口
     * @param F_Name 文件名字
     * @param F_Creator 文件创建者
     * @param F_Path 文件路径,作为哪个文件夹的子文件
     * @param F_Content 文件内容,原本是BLOB,具体能否直接用byte[]上传不确定
     */
    void proc_File_Upload(String F_Name,int F_Creator,int F_Path,byte[] F_Content);
    
    /*底下这几个都用不着*/

    int deleteByPrimaryKey(Integer fileId);

    int insert(Files row);

    int insertSelective(Files row);

    int updateByPrimaryKeySelective(Files row);

    int updateByPrimaryKey(Files row);

}