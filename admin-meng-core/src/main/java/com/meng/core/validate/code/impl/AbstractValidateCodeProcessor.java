package com.meng.core.validate.code.impl;

import com.meng.core.validate.code.ValidateCode;
import com.meng.core.validate.code.ValidateCodeGenerator;
import com.meng.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;


/**
 * @author mengye
 * @desc 验证码抽象的实现类
 * @date 2020/12/30 14:51
 */
public abstract class AbstractValidateCodeProcessor<C> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中实现了ValidateCodeGenerator
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 把验证码保存到seesion当中
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    /**
     * 从spring中依赖查找本地
     *
     * @param request
     * @return
     */
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator codeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (C) codeGenerator.generator(request);
    }

    /**
     * 调用发送抽象逻辑,由子类负责实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求切割type  /code/{type}
     *
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
