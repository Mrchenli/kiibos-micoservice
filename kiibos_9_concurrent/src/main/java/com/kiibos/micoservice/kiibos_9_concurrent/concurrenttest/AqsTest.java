package com.kiibos.micoservice.kiibos_9_concurrent.concurrenttest;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName AqsTest
 * @Description TODO
 * @Author cl
 * @Date 2019/3/8 下午3:19
 **/
public class AqsTest {
    //todo 属性

    //等待队列头部 延迟初始化，直到调用 enq才真正初始化
    private transient volatile Node head;
    //tail 等待队列尾部 延迟初始化 直到调用enq才真正初始化
    private transient  volatile Node tail;

    //aqs状态位，通过try*方法维护
    private volatile int state;

    //自旋锁超时阀值
    static final long spinForTimeoutThreshold = 1000L;


    //todo 抽象方法说明
    //非赌赛获取独占资源 true表示成功
    protected boolean tryAcquire(int arg){
        throw new UnsupportedOperationException();
    }
    //非赌赛释放独占资源，true表示成功
    protected boolean tryRelease(int arg){
        throw new UnsupportedOperationException();
    }
    //非赌赛获取共享资源 负数表示失败 0表示成功但是不需要向后传播 大于0表示成功且可以向后传播
    protected int tryAcquireShared(int arg){
        throw new UnsupportedOperationException();
    }
    //非赌赛释放共享资源 true表示成功
    protected boolean tryReleaseShared(int arg){
        throw new UnsupportedOperationException();
    }
    //在排他模式下状态是否被占用
    protected boolean isHeldExclusively(){
        throw new UnsupportedOperationException();
    }

    //todo 独占模式
    public final void acquire(int arg){
        if(!tryAcquire(arg)&&acquireQueued(addWaiter(Node.EXCLUSIVE), arg)){
            selfInterrupt();
        }
    }

    public void selfInterrupt(){

    }

    private Node addWaiter(Node mode){
        Node node = new Node(Thread.currentThread(),mode);

        Node pred = tail;
        //如果pred不为空，说明有线程在等待
        //尝试使用CAS入列，如果入列失败，则调用enq采用自旋的方式入列
        //该逻辑在无竞争的情况下才会成功快速入列
        if(pred!=null){
            node.prev = pred;
            if(compareAndSetTail(pred,node)){//通过CAS更新tail节点,关于CAS，后面会专门写篇文章介绍
                pred.next = node;
                return node;
            }
        }
        enq(node);//通过自旋入列
        return node;
    }

    boolean compareAndSetTail(Node pred,Node node){
        return false;
    }


    private Node enq(final Node node){
        for (;;){
            Node t = tail;//记录尾部节点
            if(t == null){//如果没有人在等
                if(compareAndSetHead(new Node())){
                    tail = head;
                }
            }else{//如果诶突然来了个人
                node.prev = t;//将node的前节点设置为原tail节点
                if(compareAndSetTail(t,node)){
                    t.next=node;
                    return t;
                }
            }
        }
    }

    boolean compareAndSetHead(Node node){
        return false;
    }


    final boolean acquireQueued(final Node node,int arg){
        boolean failed = true;
        try{
            boolean interrupted = false;//未发生中断
            for(;;){
                final Node p = node.predecessor();//获取前节点
                if(p==head && tryAcquire(arg)){
                    //获取资源成功，将node设置为头节点，setHead清空节点属性thread prev
                    setHead(node);
                    p.next=null;//将原头节点的next设为null 帮助GC
                    failed=false;
                    return interrupted;
                }

                if(shouldParkAfterFailedAcquire(p,node)&&
                        parkAndCheckInterrupt()){
                    interrupted = true;//发生中断
                }
            }
        }finally {
            if(failed){//只有循环中出现异常，才会进入该逻辑
                cancelAcquire(node);
            }
        }
    }

    void setHead(){

    }

    private static boolean shouldParkAfterFailedAcquire(Node pred,Node node){
        //如果acquireQueue第一次调用该方法，ws==0
        int ws = pred.waitStatus;
        //已经设置啦状态 由于SIGNAL表示通过unpark唤醒后一节点，因此获取失败时候，要调用park赌赛的返回true
        if(ws == Node.SIGNAL){
            return true;
        }
        if(ws>0){
            do {

            }while (pred.waitStatus>0);
            pred.next=node;//更新next指针，去掉中间取消状态的节点
        }else{//更新pred节点的waitstatus为SIGNAL
            compareAndSetWaitStatus(pred,ws,Node.SIGNAL);
        }
        return false;//返回false，表示不需要调用park
    }

    static boolean compareAndSetWaitStatus(Node pre,int ws,int sig){
        return false;
    }

    private final boolean parkAndCheckInterrupt(){
        //将当前线程的parkBlocker变量指向this，调用unsafe.park赌赛当前线程
        //简单来说park是申请许可，如果存在许可，马上返回，否则一直等待获取许可；unpark是将许可数量设置为1，会唤醒park返回
        //LockSupport提供了unpark(Thread thread)方法，可以为指定线程颁发许可
        //如果想更多了解，请阅读<<java如何实现线程赌赛>>这篇文章
        LockSupport.park(this);
        return Thread.interrupted();//注意：该方法会清除线程的中断状态
    }


    private void cancelAcquire(Node node){
        if(node==null){
            return;
        }

        node.thread=null;
        Node pred = null;
        //如果发现前向节点状态为cancelled 继续向前 直到找到状态正常的节点
        while (pred.waitStatus>0){
            node.prev=pred=pred.prev;
        }
        Node preNext = pred.next;
        node.waitStatus = Node.CANCELLED;//节点状态设为CANCELLED

        if(node==tail&&compareAndSetTail(node,pred)){
            compareAndSetNext(pred,preNext,null);
        }else{//如果node不是尾节点
            int ws;
            if(pred!=head&&
                    ((ws = pred.waitStatus)==Node.SIGNAL||
                            (ws<=0&&compareAndSetWaitStatus(pred,ws,Node.SIGNAL)))&&
            pred.thread != null){
                Node next = node.next;
                if(next!=null&&next.waitStatus<=0){
                    compareAndSetNext(pred,preNext,next);
                }
            }else{
                unparkSuccessor(node);//如果pred为头节点，则唤醒node的后节点
            }
            node.next= node;//help gc
        }
    }

    void compareAndSetNext(Node pred,Node preNext,Node next){

    }

    private void unparkSuccessor(Node node){
        int ws = node.waitStatus;
        if(ws<0){
            compareAndSetWaitStatus(node,ws,0);
        }
        Node s = node.next;
        if(s==null||s.waitStatus>0){
            s=null;
            for (Node t = tail;t!=null&&t!=node;t=t.prev)
                if(t.waitStatus<=0)
                    s=t;
        }

        if(s!=null) {//取出线程对象 通过unpark颁发许可
            LockSupport.unpark(s.thread);
        }
    }

    public final boolean release(int arg){
        if(tryRelease(arg)){
            Node h = head;
            if(h!=null&&h.waitStatus!=0){
                unparkSuccessor(h);//unpark唤醒第一个等待节点
            }
            return true;
        }
        return false;
    }


    //todo 共享模式方法
    //用于共享模式下获取资源，该方法会忽略中断：
    public final void acquireShared(int arg){
        if(tryAcquireShared(arg)<0){
            doAcquireShared(arg);
        }
    }

    private void doAcquireShared(int arg){
        final Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try{
            boolean interrupted = false;
            for (;;){
                final Node p = node.predecessor();
                if(p==head){
                    int r = tryAcquireShared(arg);
                    if(r>=0){
                        setHeadAndPropagate(node,r);
                        p.next = null;
                        if(interrupted){
                            selfInterrupt();//恢复中断状态
                        }
                        failed = false;
                        return;
                    }
                }
                if(shouldParkAfterFailedAcquire(p,node)&&
                        parkAndCheckInterrupt()){
                    interrupted = true;
                }
            }
        }finally {
            if(failed){
                cancelAcquire(node);
            }
        }
    }

    private void setHeadAndPropagate(Node node,int propagate){
        Node h = head;
        setHead(node);//见前面的分析
        if(propagate>0||h==null||h.waitStatus<0){
            Node s = node.next;
            if(s==null||s.isShared()){
                doReleaseShared();
            }
        }
    }
    void setHead(Node node){

    }


    private void doReleaseShared(){
        for (;;){
            Node h = head;
            if(h!=null&&h!=tail){//如果等待队列中有等待线程
                int ws = h.waitStatus;
                if(ws == Node.SIGNAL){//需要unpark后面节点
                    if(!compareAndSetWaitStatus(h,Node.SIGNAL,0)){
                        continue;
                    }
                    unparkSuccessor(h);
                }else if(ws == 0&&
                        !compareAndSetWaitStatus(h,0,Node.PROPAGATE)){
                    continue;
                }
            }
            if(h==head){
                break;
            }
        }
    }


    public final boolean releaseShared(int arg){
        if(tryReleaseShared(arg)){
            doReleaseShared();
            return true;
        }
        return false;
    }




    /**
     * @Author kiibos
     * @Description //TODO 内部类
     *
     * todo countdownlatch 的await方法可以在多个线程中调用，当CountDownLatch的计数起为0后，调用await的方法都会一次返回 共享模式
     *
     * todo reentrantLock 提供了lock和unlock方法，只允许一个线程获得锁，因此它适合被设计成独占模式，因为它获取的是一个独占资源
     *
     * todo Semaphore维护了一组许可，acquire方法获取许可，如果有可用的许可，方法返回，否则block
     *
     * todo ReentrantReadWriteLock提供了读写锁，写操作是独占的，读操作是可以彼此共享的，因此它同时使用了独占和共享模式；
     *
     *
     * @Date 下午4:09 2019/3/8
     * @param
     * @return
     **/
    static final class Node{
        //todo 共享锁是允许有多个线程同时持有资源
        static final Node SHARED = new Node();//表示等待节点处于共享模式
        //todo 每次只能又一个线程能持有资源
        static final Node EXCLUSIVE = null;//表示等待节点处于独占模式

        static final int CANCELLED = 1;//由于超时或中断，节点已被取消
        static final int SIGNAL = -1;//表示下一个节点是通过park堵塞的 需要通过unpark唤醒
        static final int CONDITION = -2;//表示线程正在等待条件变量
        static final int PROPAGATE = -3;//表示后续节点会传播唤醒的操作，共享模式下起作用

        //等待状态：对于condition节点，初始化为CONDITION;其他情况默认为0，通过cas操作原子更新
        volatile  int waitStatus;
        //前节点
        volatile Node prev;
        //后节点
        volatile Node next;
        //线程对象
        volatile Thread thread;
        //对于Condition表示下一个等待变量的节点；其他情况下用于区分共享模式和独占模式
        Node nextWaiter;

        final boolean isShared(){
            return nextWaiter==SHARED;//判断是否共享模式
        }
        //获取前节，如果为null 抛出异常
        final Node predecessor() throws NullPointerException{
            Node p  = prev;
            if(p==null){
                throw new NullPointerException();
            }else{
                return p;
            }
        }

        Node(){

        }

        Node(Thread thread,Node mode){//addWaiter方法使用
            this.nextWaiter=mode;
            this.thread = thread;
        }

        Node(Thread thread,int waitStatus){//Condition使用
            this.waitStatus = waitStatus;
            this.thread=thread;
        }


    }



}
