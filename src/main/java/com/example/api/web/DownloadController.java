package com.example.api.web;

import com.example.api.pojo.Blobs;
import com.example.api.pojo.Files;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Download")
public class DownloadController {

    /**
     * 返回指定文件的内容
     * @Param files 下载指定文件
     * @return Blobs 文件的内容
     */
    @RequestMapping("/file")
    @ResponseBody
    public Blobs file(@RequestBody Files files) {
        System.out.println("download/file");
        System.out.println(files.toString());

        return new Blobs();
    }
}
