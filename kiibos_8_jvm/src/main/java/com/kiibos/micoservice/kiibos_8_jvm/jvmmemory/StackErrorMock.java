package com.kiibos.micoservice.kiibos_8_jvm.jvmmemory;

/**
 * @ClassName StackErrorMock
 * @Description TODO
 * @Author cl
 * @Date 2019/3/5 下午2:05
 **/
public class StackErrorMock {

    private static int index = 1;

    public void call(){
        index++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        }catch (Throwable e){
            System.out.println("Stack deep:"+index);
            e.printStackTrace();
        }
    }

}
