package com.example.api.web;

import com.example.api.pojo.Files;
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
    /**
     * 上传文件
     * @return 上传是否成功
     */
    @RequestMapping("/file")
    @ResponseBody
    public boolean file(@RequestBody Files files) {
        System.out.println("upload/file");

        //这里将来会被业务逻辑取代
        System.out.println(files.toString());

        return true;
    }
}
