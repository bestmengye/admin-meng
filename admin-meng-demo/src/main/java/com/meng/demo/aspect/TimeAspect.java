package com.meng.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/19 10:02
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {

    /*@Around("execution(* com.meng.demo.controller..*.*(..))")
    public Object TimeAspect(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
        }

        //执行方法 获取到返回值.!!
        Object proceed = pjp.proceed();

        return proceed;
    }*/


    @Before("execution(* com.meng.demo.controller..*.*(..))")
    public void timeBefore(JoinPoint jp) throws Throwable {

        Class<?> aClass = jp.getTarget().getClass();
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            log.info("Aspect---Before 类上注解:{}", annotation);
        }
        /*String simpleName = aClass.getSimpleName();*/

        /*String methodName  = jp.getSignature().getUsername();-*/
        log.info("------------Aspect---Before---end---------------");
        /*Method method = aClass.getMethod(methodName);
        Annotation[] methodAnnotations = method.getAnnotations();
        for (Annotation methodAnnotation : methodAnnotations) {
        }*/

    }

    @AfterThrowing("execution(* com.meng.demo.controller..*.*(..))")
    public void timeThrowing(JoinPoint jp) throws Throwable {
        log.info("------------------Aspect--AfterThrowing---start---------------");

        Object[] args = jp.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("args:" + args[i]);
        }

        log.info("------------------Aspect--AfterThrowing---end---------------");
    }

    @AfterReturning("execution(* com.meng.demo.controller..*.*(..))")
    public void timeReturn1(JoinPoint joinPoint) throws Throwable {
        log.info("------------------AfterReturning---start---------------");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("args:" + args[i]);
        }
        log.info("------------------AfterReturning---end---------------");
    }

}
