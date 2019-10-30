package com.shiyi.test.controller;

import com.shiyi.test.exception.NoPermissionException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:40 2019/10/18
 */
@RestController
@ControllerAdvice
public class AdviceController {

    @Autowired
    public HttpServletResponse httpServletResponse;
    /**
     * shiro权限错误
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @CrossOrigin
    public String authorizationException(AuthorizationException ex) {
        if (ex instanceof UnauthenticatedException) {
            return "token错误或未登录";
        } else if (ex instanceof UnauthorizedException) {
            return "用户无权限";
        } else {
            return ex.getMessage();
        }
    }

    @ExceptionHandler(NoPermissionException.class)
    @CrossOrigin
    public String noPermissionException(NoPermissionException ex) {
        return ex.getMessage();
    }
}
