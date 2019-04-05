package com.kiibos.micoservice.galtingtest.cglib.callbackfilter;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @ClassName CallbackFilterDemo
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 下午2:57
 **/
public class CallbackFilterDemo {

    public static void main(String[] args) {
        Callback[] callbacks = new Callback[] {
                new MethodInterceptorImpl(), NoOp.INSTANCE
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyClass.class);
        enhancer.setCallbacks(callbacks);//calbacks
        enhancer.setCallbackFilter(new CallbackFilterImpl());
        MyClass myClass = (MyClass) enhancer.create();
        myClass.method();
        myClass.method1();
    }

    private static class CallbackFilterImpl implements CallbackFilter{

        @Override
        public int accept(Method method) {
            if(method.getName().equals("method")){
                return 1;
            }else{
                return 0;
            }
        }
    }

    private static class MethodInterceptorImpl implements MethodInterceptor{

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            System.err.println("Before invoke " + method);
            Object result = methodProxy.invokeSuper(o, objects);
            System.err.println("After invoke" + method);
            return result;
        }
    }



}


class MyClass{

    public void method(){
        System.out.println("MyClass.method()");
    }

    public void method1(){
        System.out.println("MyClass.method()1");
    }

}