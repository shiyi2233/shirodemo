package com.shiyi.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 10:06 2019/10/22
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.shiyi.test")
@EnableDiscoveryClient
public class Annotation_App {
    public static void main(String[] args){
        SpringApplication.run(Annotation_App.class,args);
    }
}
