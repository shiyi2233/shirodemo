package com.shiyi.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:49 2019/10/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AdminUser implements Serializable {
    private Integer id;
    private String userName;
    private String passWord;
    private String sex;
    private String address;
}
