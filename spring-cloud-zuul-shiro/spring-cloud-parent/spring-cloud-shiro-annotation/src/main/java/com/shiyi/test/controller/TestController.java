package com.shiyi.test.controller;

import com.shiyi.test.annotation.PermissionAuthentication;
import com.shiyi.test.api.ShiroControllerService;
import com.shiyi.test.api.UserRestService;
import com.shiyi.test.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:30 2019/10/22
 */
@RestController
public class TestController {
    @Autowired
    private UserRestService userRestService;
    @Autowired
    private ShiroControllerService shiroControllerService;

    @RequestMapping("/list")

    public String list(HttpServletRequest httpRequest){
        // 在session中保存用户信息
        HttpSession session = httpRequest.getSession(true);
//        System.out.println(session.getId());
       // session.setAttribute(Constants.SESSION_KEY_USER + userToken, user);
        // 存储sessionId
       // redisService.hmSet(Constants.SESSION_ID_KEY , userToken, session.getId());
        String user = userRestService.getUser();
        //System.out.println(user);

        Cookie[] cookies =  httpRequest.getCookies();
        System.out.println("=========================================>");
        if(cookies != null){
            for(Cookie cookie : cookies){
                System.out.println(cookie.getName()+":"+cookie.getValue());
            }
        }
        return "list";
    }


    @GetMapping("/shiroAll")
    @PermissionAuthentication({"shiroAll"})
    public Map<String,Object> shiroAll(){
        Map<String,Object> map = new HashMap<>();
        map.put("key","success");
        return map;
//        return shiroControllerService.shiroAll();
    }
}
