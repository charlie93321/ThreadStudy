package com.wt.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/16  21:09]
 * @DESC:
 */
public class Test_11_01 {
    private static ReentrantLock lock=new ReentrantLock();



    public static void main(String[] args){

        Thread t1=new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i <10 ; i++) {

                    Thread.sleep(500);

                    System.out.println("m1() method"+i);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });


        Thread t2=new Thread(() -> {
            boolean isLock=false;
            try {
                isLock=lock.tryLock(4,TimeUnit.SECONDS);
                if(isLock)
                        System.out.println("m2() method 非阻塞");
                else
                    System.out.println("m2() method 阻塞");

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(isLock) lock.unlock();
            }
        });

        t1.start();t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();



    }
}
