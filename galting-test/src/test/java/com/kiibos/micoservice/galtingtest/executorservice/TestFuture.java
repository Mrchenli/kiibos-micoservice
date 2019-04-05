package com.kiibos.micoservice.galtingtest.executorservice;

import java.util.concurrent.*;

/**
 * @ClassName TestFuture
 * @Description TODO
 * @Author cl
 * @Date 2019/4/1 上午8:30
 **/
public class TestFuture {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task=new Task();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");

        ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(10,20,30,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        Future<Integer> submit = threadPoolExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });

    }
}



class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i=0;i<100;i++){
            sum +=i;
        }
        return sum;
    }
}