package com.web.FileTran.service;

import com.web.FileTran.pojo.po.Comments;
import com.web.FileTran.pojo.po.Files;
import com.web.FileTran.pojo.po.Users;

import java.util.List;

/**
 * @author JeffreySharp
 * @apiNote 用于search controller 的服务类
 */
public interface SearchService {
    /**
     * 根据给定的关键词查找对应的files
     *
     * @param keyWords 给定的关键字数组
     * @return 查找到的files列表
     */
    List<Files> byKeyWords(String[] keyWords);

    /**
     * 查找所有的files
     *
     * @return file列表
     */
    List<Files> allFiles();

    /**
     * 根据下标和页的大小查询file
     *
     * @param index    当前是第几页
     * @param pageSize 当前页的大小
     * @return 查找到的结果
     */
    List<Files> fileFromIndex(int index, int pageSize);

    /**
     * 查询指定用户的某一个上传文件下的所有文件
     *
     * @param users 给定的user
     * @param files 需要上传的file
     * @return 查询到的所有文件
     */
    List<Files> uploadFiles(Users users, Files files);

    /**
     * 查询指定用户的某一个下载文件下的所有文件
     *
     * @param users 给定的user
     * @param files 给定的下载的file
     * @return 查询到的所有文件
     */
    List<Files> downloadFiles(Users users, Files files);

    /**
     * 查询指定用户的某一个收藏文件夹下的所有文件
     *
     * @param users 给定的user
     * @param files 给定的收藏的file
     * @return 查询到的所有文件
     */
    List<Files> favoriteFiles(Users users, Files files);

    /**
     * 查询指定files的评论
     *
     * @param files 给定的文件
     * @return 查询到的comments
     */
    Comments contentsFromFile(Files files);
}
