package com.fastcampus.ch3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;



@Component
@Aspect
public class LoggingAdvice {
    @Around("execution(* com.fastcampus.ch3.aop.MyMath.*(..))") //-> Pointcut ( 부가기능(advice)이 적용될 메서드의 패턴 )
//    @Around("execution(* com.fastcampus.ch3.aop.*.*(..))")
    public Object methodCallLog(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("<<[Start] "+ pjp.getSignature().getName() + Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed(); //target 의 메서드를 호출

        System.out.println("result = "+ result);

        System.out.println("[End]>>"  + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}
