package com.wt.treads2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/20  21:19]
 * @DESC:  公平锁
 */
public class Test_02_02 {
    private static  ReentrantLock fairLock=new ReentrantLock(true);
    private static List<String> threadList=new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        int num=10;
        CountDownLatch countDownLatch=new CountDownLatch(2);
        new Thread(()->{
            for (int i = 0; i < num ; i++) {
                fairLock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadList.add(Thread.currentThread().getName());

                fairLock.unlock();
            }
            countDownLatch.countDown();
        },"线程A").start();


        new Thread(()->{
            for (int i = 0; i < num ; i++) {
                fairLock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadList.add(Thread.currentThread().getName());

                fairLock.unlock();
            }
            countDownLatch.countDown();
        },"线程B").start();

        countDownLatch.await();
        System.out.println(threadList);




    }
}
