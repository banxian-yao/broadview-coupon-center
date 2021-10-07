package com.broadview.mvc.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value = "/mvcdemo", method = RequestMethod.GET, produces = "application/json")
    public String test(){
        return "helloWorld from MVC controller";
    }

}
