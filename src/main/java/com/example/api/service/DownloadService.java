package com.example.api.service;

import com.example.api.pojo.Blobs;
import com.example.api.pojo.Files;

/**
 * @author JeffreySharp
 * @apiNote 为download controller 提供的服务接口
 */
public interface DownloadService {
    /**
     * 下载文件的具体内容
     * @param files 给定的file对象
     * @return 文件的具体内容
     */
    Blobs file(Files files);
}
