package com.shiyi.test.config;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Enumeration;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:30 2019/10/18
 */
public class SessionConfig extends DefaultWebSessionManager {

    public SessionConfig() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader("token");
//        Enumeration<String> headerNames = WebUtils.toHttp(request).getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                String value = WebUtils.toHttp(request).getHeader(name);
//                System.out.println("name:"+name);
//                System.out.println("value:"+value);
//            }
//        }

        if (!StringUtils.isEmpty(id) && !"null".equals(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
