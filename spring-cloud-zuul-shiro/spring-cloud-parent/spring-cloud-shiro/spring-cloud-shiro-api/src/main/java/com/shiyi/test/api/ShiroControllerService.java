package com.shiyi.test.api;

import com.shiyi.test.entity.AdminUser;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 17:42 2019/10/23
 */
@FeignClient(name = "focus-base")
@RequestMapping("/shiro")
public interface ShiroControllerService {
    @GetMapping(value = "/verificationUser")
    public String verificationUser(AdminUser adminUser);
    @PostMapping(value = "/verificationUser" )
    public String verificationUserPost(@RequestBody AdminUser adminUser);

    @GetMapping("shiroAll")
    public Map<String,Object> shiroAll();

//    /**
//     * 权限认证
//     * @param values
//     * @param logical
//     */
//    void isPermissionAuthentication(String[] values, Logical logical);

    @RequestMapping("/checkPermissionAny")
    public String checkPermissionAny(@RequestParam("values")String[] values);
    @RequestMapping("/checkPermissionAll")
    public String checkPermissionAll(@RequestParam("values")String[] values);
}
