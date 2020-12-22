package com.meng.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author mengye
 * @desc
 * @date 2020/12/22 16:39
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
