package com.meng.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/18 15:04
 */
@Data
@AllArgsConstructor
public class ErrorException extends RuntimeException{
    private Integer code;
    private String message;
}
