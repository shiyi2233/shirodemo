package com.shiyi.test.controller;

import com.shiyi.test.api.ShiroControllerService;
import com.shiyi.test.entity.AdminUser;
import com.shiyi.test.exception.NoPermissionException;
import com.shiyi.test.tool.ShiroPermissionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:47 2019/10/18
 */
@RestController
public class ShiroController implements ShiroControllerService {
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/freemarker/login");
    }

    /**
     * 验证用户信息，用于登陆
     * @param adminUser
     * @return
     */
    @Override
    public String verificationUser(AdminUser adminUser) {
        //验证用户信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(adminUser.getUserName(), adminUser.getPassWord());
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //完成登录
            subject.login(usernamePasswordToken);
            return subject.getSession().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @Override
    public String verificationUserPost(@RequestBody AdminUser adminUser) {
       return this.verificationUser(adminUser);
    }


    @RequiresPermissions("shiro:all")
    @Override
    public Map<String,Object> shiroAll(){
        Subject subject = SecurityUtils.getSubject();
        String UserName = subject.getPrincipal().toString().split(":")[0];
        Map<String,Object> map = new HashMap<>();
        map.put("userName", UserName);
        map.put("value", "有权限");
        return map;
    }

    @GetMapping("noAuthority")
    @RequiresPermissions("noAuthority")
    public Map<String,Object> noAuthority(){
        Subject subject = SecurityUtils.getSubject();
        String UserName = subject.getPrincipal().toString().split(":")[0];
        Map<String,Object> map = new HashMap<>();
        map.put("value", "无权限");
        map.put("userName", UserName);
        return map;
    }

    @Override
    public String checkPermissionAny(String[] values) {
        try {
            ShiroPermissionUtils.checkPerissionAny(values);
        }catch (NoPermissionException ex){
            return "NoPermission";
        }
        return "success";
    }

    @Override
    public String checkPermissionAll(String[] values) {
        try {
            ShiroPermissionUtils.checkPerissionAll(values);
        }catch (NoPermissionException ex){
            return "NoPermission";
        }
            return "success";
    }
    /**
     * 手动退出
     * @param httpServletRequest
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpServletRequest httpServletRequest) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return  "退出成功";
    }

    /**
     * 自动退出 退出后会重定向到跟目录
     * @return
     */
    @GetMapping("logout2")
    public String logout2() {
        return  "退出成功";
    }
}
