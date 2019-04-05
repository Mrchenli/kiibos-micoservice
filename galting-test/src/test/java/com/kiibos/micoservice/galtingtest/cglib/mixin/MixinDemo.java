package com.kiibos.micoservice.galtingtest.cglib.mixin;

import org.springframework.cglib.proxy.Mixin;

/**
 * @ClassName MixinDemo
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 下午3:09
 **/
public class MixinDemo {

    public static void main(String[] args) {
        Class<?> [] interfaces = new Class[]{
                MyInterfaceA.class,MyInterfaceB.class
        };
        Object[] delegates = new Object[]{
                new MyInterfaceAImpl(),
                new MyInterfaceBImpl()
        };
        Object o = Mixin.create(interfaces,delegates);

        MyInterfaceA a = (MyInterfaceA) o;
        a.methodA();
        MyInterfaceB b = (MyInterfaceB) o;
        b.methodB();
    }

}
