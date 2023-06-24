package com.web.FileTran.service.impl;

import com.web.FileTran.dao.BlobsMapper;
import com.web.FileTran.pojo.po.Blobs;
import com.web.FileTran.pojo.po.Files;
import com.web.FileTran.service.DownloadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DownloadServiceImpl implements DownloadService {
    //自动填充的blob mapper 对象
    @Autowired
    private BlobsMapper blobsMapper;

    /**
     * Transactional表示事务开启
     *
     * @param files 给定的file对象
     * @return 返回对应的blobs对象
     */
    @Transactional
    @Override
    public Blobs file(Files files) {
        //返回当前文件中文件内容的主键交给blob mapper查询
        return blobsMapper.selectByPrimaryKey(files.getFileData());
    }
}
