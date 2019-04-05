package com.kiibos.micoservice.galtingtest.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @ClassName EnhancerDemo
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 下午2:49
 **/
public class EnhancerDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback(new MethodInterceptorImpl());

        EnhancerDemo demo = (EnhancerDemo) enhancer.create();
        demo.test();
        System.out.println(demo);
    }

    public void test() {
        System.out.println("EnhancerDemo test()");
    }


}
