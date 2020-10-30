package com.aop.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogUtils {

    //JoinPoint封装了当前目标方法的详细信息
    //@Before(value = "execution(public int com.aop.cal.MyCalculator.*(int,int))")
    public static void start(JoinPoint joinPoint){
        System.out.println("用到的是"+joinPoint.getSignature().getName()+"方法,用的参数是"+Arrays.asList(joinPoint.getArgs()));
    }

    //@AfterReturning(value = "execution(public int com.aop.cal.MyCalculator.*(int,int))",returning = "result")
    public static void result(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"方法执行的结果是"+result);
    }

    //@AfterThrowing(value = "execution(public int com.aop.cal.MyCalculator.*(int,int))",throwing = "exception")
    public static void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+"抛出了异常"+exception);
    }
    @Around("execution(public int com.aop.cal.MyCalculator.*(int,int))")
    public static Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args=proceedingJoinPoint.getArgs();
        String name=proceedingJoinPoint.getSignature().getName();
        Object proceed=null;
        try {
            System.out.println("用到的是" + name + "方法,用的参数是" + Arrays.asList(args));
            //利用反射调用目标方法,等价于method.invoke(obj,args);
            proceed = proceedingJoinPoint.proceed(args);
            System.out.println(name+"方法执行的结果是"+proceed);
        }catch (Exception e){
            System.out.println(name+"抛出了异常"+e);
        }finally {
            System.out.println(name+"方法结束");
        }
        return proceed;
    }
}
