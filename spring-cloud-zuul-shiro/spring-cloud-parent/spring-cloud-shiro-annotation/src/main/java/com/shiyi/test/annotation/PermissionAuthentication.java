package com.shiyi.test.annotation;

import org.apache.shiro.authz.annotation.Logical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: shiyi
 * @Description:
 * 这个玩意儿的作用是重写RequiresPermissions注解，使用微服务架构之后如果将权限认证的方法集中配置引用还是显得太臃肿，
 * 所以尝试使用feign的方式实现，具体的实现方法不变主要是要将认证路由到shiro的微服务中就行
 * 这样应该会好点具体行不行得先试试
 * @Date: Create in 16:15 2019/10/18
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionAuthentication {
    String[] value();

    Logical logical() default Logical.AND;
}
