package com.shiyi.test.service.impl;

import com.shiyi.test.entity.AdminUser;
import com.shiyi.test.service.ActiveUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 10:08 2019/10/18
 */
@Service
@Transactional
public class ActiveUserServiceImpl implements ActiveUserService {
    @Override
    public AdminUser verificationUser(AdminUser adminUser) {
        if(StringUtils.equals(adminUser.getUserName(),"admin")){
            return new AdminUser(1,"admin","123456","12","1234");
        }
        return null;
    }
}
