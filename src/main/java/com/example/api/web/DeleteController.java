package com.example.api.web;

import com.alibaba.fastjson.JSON;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Delete")
public class DeleteController {
    /**
     * 删除指定用户的指定文件
     * @return 删除是否成功
     */
    @RequestMapping("/fileFromUser")
    @ResponseBody
    public boolean fileFromUser(@RequestBody HashMap<String, Object> params) {
        System.out.println("delete/fileFromUser");

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
