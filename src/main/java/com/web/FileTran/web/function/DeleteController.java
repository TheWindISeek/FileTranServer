package com.web.FileTran.web.function;

import com.alibaba.fastjson.JSON;
import com.web.FileTran.pojo.po.Files;
import com.web.FileTran.pojo.po.Users;
import com.web.FileTran.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/Delete")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;

    /**
     * 删除指定用户的指定文件
     *
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
        String pfileString = JSON.toJSONString(params.get("pfile"));
        Files pfile = JSON.parseObject(filesString, Files.class);
//        System.out.println(users.toString());
//        System.out.println(files.toString());

        return deleteService.fileFromUser(users, files, pfile);
    }


}
