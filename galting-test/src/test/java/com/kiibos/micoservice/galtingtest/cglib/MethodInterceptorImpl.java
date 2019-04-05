package com.kiibos.micoservice.galtingtest.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Method;

/**
 * @ClassName MethodInterceptorImpl
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 下午2:46
 **/
public class MethodInterceptorImpl implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before invoke "+method);
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("After invoke"+method);
        return result;
    }
}
