package com.shiyi.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 13:56 2019/10/17
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class Base_App {
    public static void main(String[] args) {
        SpringApplication.run(Base_App.class,args);
    }
}
