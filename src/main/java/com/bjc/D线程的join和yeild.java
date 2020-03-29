package com.bjc;

/**
 * @AUTHOR: 小于
 * @DATE: [2020/3/29  12:00]
 * @DESC:
 */
public class D线程的join和yeild {


    public static void main(String[] args) {
        //testThreadJoin();
        testThreadYeild();
    }


    /**
     * 线程一>>>>>>>>>>>>:0
     * 线程一>>>>>>>>>>>>:1
     * 线程一>>>>>>>>>>>>:2
     * 线程一>>>>>>>>>>>>:3
     * 线程一>>>>>>>>>>>>:4
     * 线程一>>>>>>>>>>>>:5
     * 线程一>>>>>>>>>>>>:6
     * 线程一>>>>>>>>>>>>:7
     * 线程一>>>>>>>>>>>>:8
     * 线程一>>>>>>>>>>>>:9
     * main>>>>>>>>>>>>:0
     * main>>>>>>>>>>>>:1
     * main>>>>>>>>>>>>:2
     * main>>>>>>>>>>>>:3
     * main>>>>>>>>>>>>:4
     * main>>>>>>>>>>>>:5
     * main>>>>>>>>>>>>:6
     * main>>>>>>>>>>>>:7
     * main>>>>>>>>>>>>:8
     * main>>>>>>>>>>>>:9
     * <p>
     * <p>
     * 在线程A 中调用 线程B的join方法  线程B与线程A会串行执行,并且线程B会值执行完毕后线程A在执行
     */
    public static void testThreadJoin() {
        Thread t1 = new Thread("线程一") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>:" + i);
                }
            }
        };


        t1.start();
        try {
            t1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>:" + i);
        }


    }


    public static void testThreadYeild() {
        Thread t1 = new Thread("线程一") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>:" + i);
                }
            }
        };

        t1.start();
        for (int i = 0; i < 10; i++) {
            // 线程礼让  如果不加Thread.yield(),cpu会大概率先执行完毕main线程在执行线程1
            // 但是加了Thread.yield()会让线程main让出cpu,让线程一(处于就绪状态)获得cpu的调度而执行 加多个是为了将这种礼让的效果更明显
            // 同时注意让出cpu后,同时处于就绪状态的线程main和线程1,会争夺cpu的时间片,故礼让,不一定会让线程1获得调度,但大大增加了这种几率

            Thread.yield();Thread.yield();Thread.yield();
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>:" + i);
        }


    }


}
