package com.shiyi.test.controller;

import com.shiyi.test.api.UserRestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 14:10 2019/10/17
 */
@RestController
//@RequestMapping("/user")
public class UserController implements UserRestService{
    @Override
//    @GetMapping("/getUser")
    public String getUser() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
//        System.out.println("basesession:"+session.getId());
        System.out.println("=========================================>");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                System.out.println(cookie.getName()+":"+cookie.getValue());
            }
        }
        return "test1";
    }
}
