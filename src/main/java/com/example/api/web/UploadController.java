package com.example.api.web;

import com.alibaba.fastjson.JSON;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import com.example.api.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;
    /**
     * 上传文件
     * @return 上传是否成功
     */
    @RequestMapping("/file")
    @ResponseBody
    public boolean file(@RequestBody HashMap<String, Object> params) {
        System.out.println("upload/file");

        //这里将来会被业务逻辑取代
        String fileString = JSON.toJSONString(params.get("files"));
        Files files = JSON.parseObject(fileString, Files.class);
        String pfilesString = JSON.toJSONString(params.get("pfiles"));
        Files pfiles = JSON.parseObject(pfilesString, Files.class);
        String blob = JSON.toJSONString(params.get("blob"));
        String userString = JSON.toJSONString(params.get("users"));
        Users users = JSON.parseObject(userString, Users.class);

        //System.out.println(files.toString());

        return uploadService.file(files, pfiles, blob, users);
    }
}
