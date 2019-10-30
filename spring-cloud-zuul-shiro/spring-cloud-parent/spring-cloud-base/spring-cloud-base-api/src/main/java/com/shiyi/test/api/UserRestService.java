package com.shiyi.test.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 14:14 2019/10/17
 */
@FeignClient(name = "spring-cloud-base")
@RequestMapping("/user")
public interface UserRestService {
    @GetMapping("/getUser")
    public String getUser();
}
