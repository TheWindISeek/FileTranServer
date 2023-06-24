package com.web.FileTran.service.impl;

import com.web.FileTran.dao.CommentsMapper;
import com.web.FileTran.dao.FilesMapper;
import com.web.FileTran.pojo.po.Comments;
import com.web.FileTran.pojo.po.Files;
import com.web.FileTran.pojo.po.Users;
import com.web.FileTran.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private CommentsMapper commentsMapper;

    @Transactional
    @Override
    public List<Files> byKeyWords(String[] keyWords) {
        List<Files> files = new ArrayList<>();
        for (String keyWord : keyWords) {
            //单个查询
            List<Files> list = filesMapper.selectByFileName(keyWord);
            files.addAll(list);
        }
        return files;
    }

    @Transactional
    @Override
    public List<Files> allFiles() {
        Integer count = filesMapper.selectVisibleFileCount();
        return filesMapper.selectAnyVisibleFiles(0, count);
    }

    @Transactional
    @Override
    public List<Files> fileFromIndex(int index, int pageSize) {
        return filesMapper.selectAnyVisibleFiles(pageSize * (index - 1), pageSize);
    }

    @Transactional
    @Override
    public List<Files> uploadFiles(Users users, Files files) {
        //not implement
        return null;
    }

    @Transactional
    @Override
    public List<Files> downloadFiles(Users users, Files files) {
        return null;
    }

    @Transactional
    @Override
    public List<Files> favoriteFiles(Users users, Files files) {
        //先根据user获得收藏夹ID
        Integer favorites = users.getUserFavorites();
        //看不见
        return filesMapper.selectByFileName(files.getFileName());
    }

    @Transactional
    @Override
    public Comments contentsFromFile(Files files) {
        return commentsMapper.selectByPrimaryKey(files.getFileComments());
    }
}
