package com.kiibos.micoservice.kiibos_9_concurrent.concurrenttest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName RentryLockTest
 * @Description TODO
 *
 * 可重入锁是 一个线程 多次获取同一把锁 就计数+1
 *
 * 公平锁 非公平锁
 *
 * @Author cl
 * @Date 2019/3/8 下午3:23
 **/
public class RentryLockTest {


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        System.out.println("main 获取锁");
        lock.lock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 开始获取锁");
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("t1 release lock");
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 开始获取锁");
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("t2 release lock");
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("wake up");
        lock.unlock();
        TimeUnit.SECONDS.sleep(20);
    }




}
