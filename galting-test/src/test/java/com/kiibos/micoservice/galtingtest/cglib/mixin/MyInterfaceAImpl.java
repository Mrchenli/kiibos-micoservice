package com.kiibos.micoservice.galtingtest.cglib.mixin;

/**
 * @ClassName MyInterfaceAImpl
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 下午3:12
 **/
public class MyInterfaceAImpl implements MyInterfaceA {
    @Override
    public void methodA() {
        System.out.println("MyInterfaceAImpl.methodA()");
    }
}
