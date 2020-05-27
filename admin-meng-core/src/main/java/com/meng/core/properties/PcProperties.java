package com.meng.core.properties;

import lombok.Data;

/**
 * @author mengye
 * @desc pc的属性配置文件
 * @date 2020/5/26 21:00
 */
@Data
public class PcProperties {

    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;
}
