package com.wt.threads;

import java.util.Arrays;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/16  13:06]
 * @DESC:
 */
public class Test_10_01 {





    private static Object lock=new Object();
    static  final Test_10.Volum<Integer> vs=new Test_10.Volum<>(10);
    public static void main(String[] args){


        Runnable r1 = () -> {
            synchronized (lock) {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    vs.add(i);
                    System.out.println(Arrays.toString(vs.args));
                    if(vs.index==5){
                        try {
                            System.out.println("notify"+vs.index);
                            lock.notifyAll();
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Runnable r2 = () -> {
            synchronized (lock) {

                System.out.println("执行==>"+vs.index);
                if (vs.index != 5) {
                    try {
                        System.out.println("wait"+vs.index);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("end");
                    lock.notifyAll();
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
