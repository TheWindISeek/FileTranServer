package com.example.api.web;

import com.alibaba.fastjson.JSON;
import com.example.api.pojo.Administrator;
import com.example.api.pojo.Comments;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Search")
public class SearchController {

    /**
     * 按照关键字查询页码
     * @return list<files> 文件列表
     */
    @RequestMapping("/byKeyWords")
    @ResponseBody
    public List<Files> byKeyWords(@RequestBody String [] keyWords) {
        System.out.println("search/byKeyWords");
        //关键字是否被成功映射
        for(String s:keyWords) {
            System.out.println(s);
        }

        //这里将来会被业务逻辑取代
        List<Files> files = new ArrayList<>();
        Files file = new Files();
        file.setFileComments(2);
        file.setFileName("are you kidding me");

        return files;
    }

    /**
     * 按照前缀 使用 大小
     * @return list<files> 文件列表
     */
    @RequestMapping("/byLabels")
    @ResponseBody
    public List<Files> byLabels(@RequestBody Map<String, Object> params) {
        System.out.println("search/byLabels");
        Object usages = params.get("usages");

        //这里将来会被业务逻辑取代
        List<Files> files = new ArrayList<>();

        return files;
    }

    /**
     * 返回所有的文件
     * @return list<files> 文件列表
     */
    @RequestMapping("/allFiles")
    @ResponseBody
    public List<Files> allFiles() {
        System.out.println("search/allFiles");

        //这里将来会被业务逻辑取代
        List<Files> files = new ArrayList<>();

        return files;
    }

    /**
     * 返回所有的文件
     * @return list<files> 文件列表
     */
    @RequestMapping("/fileFromIndex")
    @ResponseBody
    public List<Files> fileFromIndex(@RequestBody HashMap<String, Integer> params) {
        System.out.println("search/fileFromIndex");

        System.out.println(params.get("index"));
        System.out.println(params.get("pageSize"));
        //这里将来会被业务逻辑取代
        List<Files> files = new ArrayList<>();

        return files;
    }

    /**
     *  查询对应用户上传的文件列表
     * @return list<files> 文件列表
     */
    @RequestMapping("/uploadFiles")
    @ResponseBody
    public List<Files> uploadFiles(@RequestBody HashMap<String, Object> params) {
        System.out.println("search/uploadFiles");

        String userString = JSON.toJSONString(params.get("users"));
        Users users = JSON.parseObject(userString, Users.class);
        String filesString = JSON.toJSONString(params.get("files"));
        Files files = JSON.parseObject(filesString, Files.class);
        System.out.println(users.toString());
        System.out.println(files.toString());
        //这里将来会被业务逻辑取代


        return new ArrayList<>();
    }

    /**
     *  查询对应用户下载的文件列表
     * @return list<files> 文件列表
     */
    @RequestMapping("/downloadFiles")
    @ResponseBody
    public List<Files> downloadFiles(@RequestBody HashMap<String, Object> params) {
        System.out.println("search/downloadFiles");

        String userString = JSON.toJSONString(params.get("users"));
        Users users = JSON.parseObject(userString, Users.class);
        String filesString = JSON.toJSONString(params.get("files"));
        Files files = JSON.parseObject(filesString, Files.class);
        System.out.println(users.toString());
        System.out.println(files.toString());

        return new ArrayList<>();
    }

    /**
     *  查询对应用户下载的文件列表
     * @return list<files> 文件列表
     */
    @RequestMapping("/favoriteFiles")
    @ResponseBody
    public List<Files> favoriteFiles(@RequestBody HashMap<String, Object> params) {
        System.out.println("search/favoriteFiles");

        String userString = JSON.toJSONString(params.get("users"));
        Users users = JSON.parseObject(userString, Users.class);
        String filesString = JSON.toJSONString(params.get("files"));
        Files files = JSON.parseObject(filesString, Files.class);
        System.out.println(users.toString());
        System.out.println(files.toString());

        return new ArrayList<>();
    }

    /**
     *  查询对应文件的Content内容
     * @return context对象
     */
    @RequestMapping("/contentsFromFileteFiles")
    @ResponseBody
    public Comments contentsFromFile(@RequestBody Files files) {
        System.out.println("search/contentsFromFile");

        System.out.println(files);

        return new Comments();
    }


}
