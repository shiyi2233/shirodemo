package com.shiyi.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 14:45 2019/8/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ActiveUser {
    private AdminUser user;
    private List<String> roles;
    private List<String> permissions;

}
