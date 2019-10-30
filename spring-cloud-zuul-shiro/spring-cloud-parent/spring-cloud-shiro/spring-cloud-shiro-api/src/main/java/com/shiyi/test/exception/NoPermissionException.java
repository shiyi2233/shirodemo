package com.shiyi.test.exception;

/**
 * @Author: shiyi
 * @Description:
 * @Date: Create in 17:27 2019/10/25
 */
public class NoPermissionException extends RuntimeException {
    public NoPermissionException(){
        super();
    }

    public NoPermissionException(String msg) {
        super(msg);
    }
}
