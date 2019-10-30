package com.shiyi.test.config;

import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: shiyi
 * @Description:
 *  * 自定义的请求头处理类，处理服务发送时的请求头；
 *  * 将服务接收到的请求头中的uniqueId和token字段取出来，并设置到新的请求头里面去转发给下游服务
 *  * 比如A服务收到一个请求，请求头里面包含uniqueId和token字段，A处理时会使用Feign客户端调用B服务
 *  * 那么uniqueId和token这两个字段就会添加到请求头中一并发给B服务；
 * @Date: Create in 10:08 2019/10/25
 */
@Configuration
public class FeignHeadConfiguration {
//    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                Cookie[] cookies = request.getCookies();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String value = request.getHeader(name);
//                        System.out.println("name:"+name);
//                        System.out.println("value:"+value);
                        /**
                         * 遍历请求头里面的属性字段，将logId和token添加到新的请求头中转发到下游服务
                         * */
                        //if ("cookie".equalsIgnoreCase(name) || "user-agent".equalsIgnoreCase(name)) {
//                            logger.debug();
//                            System.out.println("添加自定义请求头key:" + name + ",value:" + value);
                            requestTemplate.header(name, value);
//                        } else {
////                            logger.debug("FeignHeadConfiguration", "非自定义请求头key:" + name + ",value:" + value + "不需要添加!");
//                            System.out.println( "非自定义请求头key:" + name + ",value:" + value + "不需要添加!");
//                        }
                    }
                } else {
//                    logger.warn("FeignHeadConfiguration", "获取请求头失败！");
                    System.out.println("获取请求头失败！");
                }
            }
        };
    }
}
