package com.shiyi.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:06 2019/10/17
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka_App {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_App.class,args);
    }
}
