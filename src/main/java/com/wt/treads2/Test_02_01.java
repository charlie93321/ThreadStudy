package com.wt.treads2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/20  15:45]
 * @DESC:
 */
public class Test_02_01 {
    /**
     *  默认 或者 设置 false  为非公平 锁
     *  公平锁内置等待队列,根据队列 调度线程
     */
    private static ReentrantLock lock=new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {

        Thread main=Thread.currentThread();
        List<String> container=new ArrayList<>();//Collections.synchronizedList(new ArrayList<>());
        AtomicInteger count=new AtomicInteger(0);
        int num=5;
        int numThread=3;
        CountDownLatch latch=new CountDownLatch(numThread);

        Thread[] ts=new Thread[numThread];
        for (int  j = 0; j <numThread ; j++) {


            ts[j]=new Thread(()->{
                for (int i = 0; i <num ; i++) {
                    boolean isLock=true;
                    try {
                         lock.lock();
                          Thread.sleep(1000);
                         container.add(Thread.currentThread().getName() + "_" + i);

                         if(Thread.currentThread().getName().equals("2"))
                                      main.interrupt();



                    }catch (Exception e){
                          System.out.println("inter rupt ==> add "+(Thread.currentThread().getName() + "_" + i));
                    }finally {
                        count.incrementAndGet();
                        try {
                            lock.unlock();
                        }catch (IllegalMonitorStateException e){
                            System.out.println("unlock exception");
                        }
                    }

                }
                latch.countDown();
            },""+(j+1));

        }

        for (int i = 0; i <numThread; i++) {
            ts[i].start();

            //ts[i].interrupt();
        }



        try {
            latch.await();
        }catch (Exception e){
            System.out.println("eeeee the size of list is ==>"+container.size()+"== the add times is "+ count.get());
        }

        System.out.println("the size of list is ==>"+container+"== the add times is "+ count.get());

    }

}
