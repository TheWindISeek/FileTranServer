package com.web.FileTran.web.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class StaticController {
    @RequestMapping("/")
    public String index(){
        System.out.println("DemoController.index()");
        return "index";
    }
}
