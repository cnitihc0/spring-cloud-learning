package com.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mashaobo
 * @create 2017-11-01 15:17
 */
@RefreshScope
@RestController
public class TestController {
    @Autowired
    private Environment env;

    //访问地址就是http://127.0.0.1:8765/config-client/dev/dev 中的属性
    @RequestMapping("/{name}")
    public String form(@PathVariable String name) {
        return env.getProperty(name,"undefined");
    }

    @RequestMapping("/democonfigclient")
    public String message() {
        return env.getProperty("democonfigclient.message","undefined");
    }
}
