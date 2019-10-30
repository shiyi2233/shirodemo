package com.shiyi.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 10:22 2019/10/17
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWay_App {
    public static void main(String[] args){
        SpringApplication.run(GateWay_App.class,args);
    }
}
