package com.example.api.web;

import com.alibaba.fastjson.JSON;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/Update")
public class UpdateController {
    /**
     * 更新指定用户的指定文件的内容
     * @return 修改是否成功
     */
    @RequestMapping("/file")
    @ResponseBody
    public boolean file(@RequestBody HashMap<String, Object> params) {
        System.out.println("update/file");

        String userString = JSON.toJSONString(params.get("users"));
        Users users = JSON.parseObject(userString, Users.class);
        String filesString = JSON.toJSONString(params.get("files"));
        Files files = JSON.parseObject(filesString, Files.class);
        System.out.println(users.toString());
        System.out.println(files.toString());
        //这里将来会被业务逻辑取代


        return true;
    }
}
