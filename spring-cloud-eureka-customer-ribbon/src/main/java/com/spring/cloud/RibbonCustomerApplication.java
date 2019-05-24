package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 在工程的启动类中,通过@EnableDiscoveryClient向服务中心注册；
 * 并且向程序的ioc注入一个bean: restTemplate;
 * 并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能。
 */
@SpringCloudApplication
@EnableDiscoveryClient
public class RibbonCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonCustomerApplication.class, args);
    }
}
