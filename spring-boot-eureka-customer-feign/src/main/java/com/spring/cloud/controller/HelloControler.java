package com.spring.cloud.controller;

import com.spring.cloud.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mashaobo
 * @create 2017-10-19 9:54
 */
@RestController
public class HelloControler {

    @Autowired
    SchedualServiceHi schedualServiceHi;
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }


}
