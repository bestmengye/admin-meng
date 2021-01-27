package com.meng.core.authentication.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mengye
 * @desc 简单的内容返回
 * @date 2021/1/27 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponse {

    private Object content;
}
