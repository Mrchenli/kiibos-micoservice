package com.kiibos.micoservice.kiibos_9_concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName TestParkSupport
 * @Description TODO
 * @Author cl
 * @Date 2019/3/8 下午10:11
 **/
public class TestParkSupport {

    public static void main(String[] args) throws InterruptedException {
        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("comming...");
                LockSupport.park();
                System.out.println("un parking...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("un parked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        System.out.println("test thread start...");
        test.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("主线程ok unpart test线程");
        LockSupport.unpark(test);
        System.out.println("unpark ");
    }


}
