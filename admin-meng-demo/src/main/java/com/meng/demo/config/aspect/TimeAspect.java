package com.meng.demo.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/19 10:02
 */
@Aspect
@Component
public class  TimeAspect{

    /*@Around("execution(* com.meng.demo.controller..*.*(..))")
    public Object TimeAspect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("------------------Aspect执行开始------------------");

        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("args:"+args[i]);
        }

        System.out.println("------------------Aspect执行完毕------------------");
        //执行方法 获取到返回值.!!
        Object proceed = pjp.proceed();
        System.out.println("还可以通过这个分割战场？？？？先去执行业务逻辑,再根据返回参数？？来搞");

        System.out.println("返回参数"+proceed.toString());
        return proceed;
    }*/


    @Before("execution(* com.meng.demo.controller..*.*(..))")
    public void timeBefore(JoinPoint jp) throws Throwable {
        System.out.println("------------Aspect---Before---start---------------");

        Class<?> aClass = jp.getTarget().getClass();
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("Aspect---Before 类上注解:"+annotation);
        }
        /*String simpleName = aClass.getSimpleName();
        System.out.println("simpleName"+simpleName);*/

        /*String methodName  = jp.getSignature().getName();
        System.out.println("当前执行的方法name:"+methodName);*/
        System.out.println("------------Aspect---Before---end---------------");
        /*Method method = aClass.getMethod(methodName);
        Annotation[] methodAnnotations = method.getAnnotations();
        for (Annotation methodAnnotation : methodAnnotations) {
            System.out.println("方法上的注解"+methodAnnotation);
        }
        System.out.println("------------------Before---end---------------");*/

    }

    @AfterThrowing("execution(* com.meng.demo.controller..*.*(..))")
    public void timeThrowing(JoinPoint jp) throws Throwable {
        System.out.println("------------------Aspect--AfterThrowing---start---------------");

        Object[] args = jp.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("args:"+args[i]);
        }

        System.out.println("------------------Aspect--AfterThrowing---end---------------");
    }

    @AfterReturning("execution(* com.meng.demo.controller..*.*(..))")
    public void timeReturn1(JoinPoint joinPoint) throws Throwable {
        System.out.println("------------------AfterReturning---start---------------");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("args:"+args[i]);
        }
        System.out.println("------------------AfterReturning---end---------------");
    }

}
