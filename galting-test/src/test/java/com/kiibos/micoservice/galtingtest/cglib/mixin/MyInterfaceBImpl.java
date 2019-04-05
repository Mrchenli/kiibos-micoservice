package com.kiibos.micoservice.galtingtest.cglib.mixin;

/**
 * @ClassName MyInterfaceBImpl
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 下午3:13
 **/
public class MyInterfaceBImpl implements MyInterfaceB {

    @Override
    public void methodB() {
        System.out.println("MyInterfaceBImpl.methodB()");
    }
}
