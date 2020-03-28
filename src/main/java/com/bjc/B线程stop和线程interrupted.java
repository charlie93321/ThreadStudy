package com.bjc;

import com.sun.xml.internal.ws.util.StringUtils;
import lombok.Data;

/**
 * @AUTHOR: 小于
 * @DATE: [2020/3/28  20:18]
 * @DESC:
 */
public class B线程stop和线程interrupted {


    /**
     * 现在有一个对象存放股票编号和股票价格,两个线程 1个线程负责写入,另一个线程负责读取
     * 现在有三支股票的数据,三支股票的数据滚动写入这个对象中,由锁来保证读取的时候已经将一个对象修改完全,不会读取到错误的数据如股票的编号和价格不对应的情况
     * [
     * {
     * 'no':'0001',
     * 'price':1000
     * },
     * {
     * 'no':'0002',
     * 'price':2000
     * },
     * {
     * 'no':'0003',
     * 'price':3000
     * }
     * ]
     * 1. stop
     */
    @Data
    private static class GupiaoEntity {
        //股票编号
        private String no;
        //股票价格
        private double price;

        public GupiaoEntity(String no, double price) {
            this.no = no;
            this.price = price;
        }

        public GupiaoEntity() {
        }
    }

    private static final GupiaoEntity[] GupiaoData = new GupiaoEntity[]{
            new GupiaoEntity("0001", 1000), new GupiaoEntity("0002", 2000), new GupiaoEntity("0003", 3000)

    };
    // 锁
    public static final Object LOCK = new Object();
    // 对象 用于存放数据的对象
    public static volatile GupiaoEntity DATA = new GupiaoEntity();

    /**
     * 修改数据
     */
    public static final class WriteStopThread extends Thread {


        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    // 修改DATA对象中数据
                    DATA.setNo(GupiaoData[i % 3].getNo());
                    // stop
                    if (i == 5) {
                        System.out.println("写入线程被stop了>>>>>>>>>>>>>>>>>>>");
                        this.stop();
                    }
                    DATA.setPrice(GupiaoData[i % 3].getPrice());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public WriteStopThread(String name) {
            super(name);
        }
    }


    public static final class WriteInterruptedThread extends Thread {


        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    // 修改DATA对象中数据
                    DATA.setNo(GupiaoData[i % 3].getNo());
                    // stop
                    if (i == 5) {
                        System.out.println("写入线程被Interrupted了>>>>>>>>>>>>>>>>>>>");
                        this.interrupt();
                    }
                    DATA.setPrice(GupiaoData[i % 3].getPrice());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }

        }

        public WriteInterruptedThread(String name) {
            super(name);
        }
    }

    public static final class ReadThread implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (i < 20) {
                synchronized (LOCK) {
                    System.out.println("读取股票数据>>>>>>:" + DATA);

                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        //Thread wt = new WriteStopThread("写入线程");

        Thread wt = new WriteInterruptedThread("写入线程");

        wt.start();
        new Thread(new ReadThread(), "读取线程").start();


    }
}
