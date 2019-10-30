package com.shiyi.test.aspect;

import com.shiyi.test.annotation.PermissionAuthentication;
import com.shiyi.test.api.ShiroControllerService;
import com.shiyi.test.api.UserRestService;
import com.shiyi.test.exception.NoPermissionException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: shiyi
 * @Description:
 * 这里是注解的实现方式，我想应该是基于spring实现会好一些，尝试下
 * @Date: Create in 17:31 2019/10/18
 */
@Aspect
@Component
public class PermissionAuthenticationAspect {
    @Autowired
    private UserRestService userRestService;
    @Autowired
    private ShiroControllerService shiroControllerService;
    // 切入点签名
    @Pointcut("@annotation(com.shiyi.test.annotation.PermissionAuthentication)")
    private void cut() {
    }

    @Before("cut()")
    public void aroundCall(JoinPoint joinPoint){
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        HttpSession session = request.getSession();
        MethodSignature ms = (MethodSignature)joinPoint.getSignature();
        Method method = ms.getMethod();
        PermissionAuthentication myAnnotation = method.getAnnotation(PermissionAuthentication.class);
        String[] value = myAnnotation.value();
        Logical logical = myAnnotation.logical();
        String msg = shiroControllerService.checkPermissionAll(value);
        if(StringUtils.equals(msg,"NoPermission")){
            throw new NoPermissionException("no permission");
        }
    }
}
