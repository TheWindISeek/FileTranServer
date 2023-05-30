package com.example.api.web;

import com.alibaba.fastjson.JSON;
import com.example.api.pojo.Administrator;
import com.example.api.pojo.Comments;
import com.example.api.pojo.Files;
import com.example.api.pojo.Users;
import com.example.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SearchService searchService;
    /**
     * 按照关键字查询页码
     * @return list<files> 文件列表
     */
    @RequestMapping("/byKeyWords")
    @ResponseBody
    public List<Files> byKeyWords(@RequestBody String [] keyWords) {
        System.out.println("search/byKeyWords");
        //关键字是否被成功映射
//        for(String s:keyWords) {
//            System.out.println(s);
//        }
        return searchService.byKeyWords(keyWords);
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

        //no implement

        return new ArrayList<>();
    }

    /**
     * 返回所有的文件
     * @return list<files> 文件列表
     */
    @RequestMapping("/allFiles")
    @ResponseBody
    public List<Files> allFiles() {
        System.out.println("search/allFiles");

        return searchService.allFiles();
    }

    /**
     * 返回所有的文件
     * @return list<files> 文件列表
     */
    @RequestMapping("/fileFromIndex")
    @ResponseBody
    public List<Files> fileFromIndex(@RequestBody HashMap<String, Integer> params) {
        System.out.println("search/fileFromIndex");
        int index = params.get("index");
        int pageSize = params.get("pageSize");

        return searchService.fileFromIndex(index, pageSize);
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
//        System.out.println(users.toString());
//        System.out.println(files.toString());


        return searchService.uploadFiles(users, files);
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
//        System.out.println(users.toString());
//        System.out.println(files.toString());

        return searchService.downloadFiles(users, files);
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
//        System.out.println(users.toString());
//        System.out.println(files.toString());

        return searchService.favoriteFiles(users, files);
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

        return searchService.contentsFromFile(files);
    }


}
