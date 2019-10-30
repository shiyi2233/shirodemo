package com.shiyi.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 9:58 2019/10/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Permission implements Serializable {
    private Integer id;
    private String pername;
    private String percode;
}
