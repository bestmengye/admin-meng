package com.meng.demo.exception;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/18 13:59
 */

public class UserNotExistException extends RuntimeException{

    private Integer id;

    public UserNotExistException(Integer id) {
        super("用户没有找到");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
