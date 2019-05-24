package com.spring.cloud.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.spring.cloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mashaobo
 * @create 2017-10-19 9:54
 */
@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/syn")
    public String syn(@RequestParam String name){
        return helloService.hystrixCommandSyn(name);
    }

    @RequestMapping(value = "/asy")
    public String asy(@RequestParam String name){
        helloService.hystrixCommandAsy(name);
        return name;
    }

    @RequestMapping(value = "/cache")
    public String cache(@RequestParam String name){
        HystrixRequestContext.initializeContext();
        helloService.hystrixCommandCache(name);
        return helloService.hystrixCommandCache(name);
    }


}
