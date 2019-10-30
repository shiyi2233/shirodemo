package com.shiyi.test.service;

import com.shiyi.test.entity.AdminUser;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 10:07 2019/10/18
 */
public interface ActiveUserService {
    AdminUser verificationUser(AdminUser adminUser);
}
