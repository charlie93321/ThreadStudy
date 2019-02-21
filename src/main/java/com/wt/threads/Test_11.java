package com.wt.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/16  21:00]
 * @DESC:
 */
public class Test_11 {
    private static ReentrantLock lock=new ReentrantLock();



    public static void main(String[] args){

        Thread t1=new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i <10 ; i++) {

                        Thread.sleep(1000);

                    System.out.println("m1() method"+i);
                }
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread t2=new Thread(() -> {
            try {
                lock.lock();
                 System.out.println("m2() method");
                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //t1.interrupt();



    }
}
