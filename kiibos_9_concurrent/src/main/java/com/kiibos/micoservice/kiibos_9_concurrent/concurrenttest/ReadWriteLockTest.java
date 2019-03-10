package com.kiibos.micoservice.kiibos_9_concurrent.concurrenttest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockTest
 * @Description TODO
 * @Author cl
 * @Date 2019/3/8 下午10:46
 **/
public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock writeLock  = readWriteLock.writeLock();
        Lock lock  = readWriteLock.readLock();
        final Condition notFull =writeLock.newCondition();
        final Condition notNull =writeLock.newCondition();
        System.out.println("main开始获取锁");
        lock.lock();
        System.out.println("main 获取到读锁");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 开始获取锁");
                lock.lock();
                System.out.println("t1 获取到读锁");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 开始获取锁");
                lock.lock();
                System.out.println("t2 获取到读锁");
            }
        });
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("wake up");

    }
}
