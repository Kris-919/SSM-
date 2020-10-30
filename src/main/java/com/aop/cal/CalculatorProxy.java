package com.aop.cal;

import com.aop.cal.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {
    /*为传入的参数创建一个动态代理对象
    * 传入的参数为被代理的对象,返回的是动态代理的对象
    */
    //匿名内部类只能使用final修饰的对象
    public static Calculator getProxy(final Calculator calculator){
        //InvocationHandler: 方法执行器,用于执行目标对象的目标方法
        System.out.println(calculator.getClass());
        InvocationHandler h=new InvocationHandler() {
            /* Object proxy: 代理对象,给JDK用的,任何时候都不要使用这个对象
            * Method method: 当前将要执行的目标对象的方法
            * Object[] args: 这个方法调用时外界传入的参数值
            */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result=method.invoke(calculator,args);//利用反射执行目标方法
                return result;
            }
        };
        Class<?>[]interfaces=calculator.getClass().getInterfaces();
        ClassLoader loader=calculator.getClass().getClassLoader();
        //Proxy为目标对象创建代理对象
        Object proxy= Proxy.newProxyInstance(loader,interfaces,h);
        return (Calculator)proxy;
    }
}
