package com.broadview.nacosdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DemoController {
    @Value("${title}")
    private String title;

    @Value("${name}")
    private String name;

    @GetMapping("/nacos/config")
    public String test(){
        return "title:"+title+",name:"+name;
    }
}
