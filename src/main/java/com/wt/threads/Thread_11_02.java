package com.wt.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/16  21:16]
 * @DESC:
 */
public class Thread_11_02 {
    private static ReentrantLock lock=new ReentrantLock();



    public static void main(String[] args){

        Thread t1=new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i <10 ; i++) {

                    Thread.sleep(5000);

                    System.out.println("m1() method"+i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });


        Thread t2=new Thread(() -> {

            try {
                lock.lockInterruptibly();
                System.out.println("m2() method 执行");

            } catch (Exception e) {

                System.out.println("m2() method 中断"+e);
            }finally {
                try {
                    lock.unlock();
                }catch (Exception e){}
            }
        });

        t1.start();t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();



    }
}
