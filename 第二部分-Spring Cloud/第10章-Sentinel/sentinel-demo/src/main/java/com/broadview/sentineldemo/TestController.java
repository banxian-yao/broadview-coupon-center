package com.broadview.sentineldemo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/hello/{name}")
    @SentinelResource(value = "sayHello")
    public String apiHello(@PathVariable String name) {
        return "hello world"+name;
    }

    @ExceptionHandler(value = {FlowException.class})
    public String sentinelExceptionHandle(Model model, FlowException e) {
        return "Flow controlled by Sentinel";
    }
}
