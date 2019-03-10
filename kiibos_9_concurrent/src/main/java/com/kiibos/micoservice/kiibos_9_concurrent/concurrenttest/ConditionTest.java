package com.kiibos.micoservice.kiibos_9_concurrent.concurrenttest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionTest
 * @Description TODO
 * @Author cl
 * @Date 2019/3/8 下午11:01
 **/
public class ConditionTest {

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();

    private final Condition notFull = lock.newCondition();

    public void putLast(Object a){
        if (a == null) throw new NullPointerException();
        lock.lock();
        try {
            while (!linkLast(a))
                notFull.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private boolean linkLast(Object a){
        return false;
    }

}
