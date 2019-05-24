package com.spring.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author mashaobo
 * @create 2017-10-19 9:55
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    // 同步hystrixCommand
//    @HystrixCollapser(collapserKey="collapserKey", batchMethod="hystrixCommandSynAll",
//            scope= com.netflix.hystrix.HystrixCollapser.Scope.REQUEST,
//            collapserProperties={})
//    @HystrixCommand(groupKey = "groupKey1", commandKey="commandKey1", threadPoolKey = "threadPoolKey1", fallbackMethod = "fallBack")
    public String hystrixCommandSyn(String name) {
        return restTemplate.getForObject("http://PRODUCTOR-SERVICE/hi?name={1}", String.class, name);
    }

    public String fallBack(String name) {
        return "hi,"+name+",sorry,error!";
    }

    // 异步hystrixCommand
    @HystrixCommand(groupKey = "groupKey2", commandKey="commandKey2", threadPoolKey = "threadPoolKey2")
    public Future<String> hystrixCommandAsy(String name) {
        return new AsyncResult<String>(){
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://PRODUCTOR-SERVICE/hi?name="+name, String.class).getBody();
            }
        };
    }

    // 缓存hystrixCommand
    @CacheResult
    @HystrixCommand(groupKey = "groupKey2",threadPoolKey = "threadPoolKey2")
    public String hystrixCommandCache(@CacheKey String name) {
        return restTemplate.getForEntity("http://PRODUCTOR-SERVICE/hi?name="+name, String.class).getBody();
    }

    private String cacheKeyMethod(String name) {
        return "hystrixCommandCache";
    }


    public List<String> hystrixCommandSynAll(List<String> names) {
        return restTemplate.getForObject("http://PRODUCTOR-SERVICE/his?name={1}", List.class, StringUtils.join(names, ","));
    }
}
