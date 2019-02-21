package com.wt.threads;

import scala.math.Ordering;

import java.util.Random;

import static com.wt.utils.Print.println;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/2  17:21]
 * @DESC:
 *
 * 1.在静态同步方法中,锁的是字节码对象
 */
public class Test_02 {
    public static int staticCount=1;
    private static final Object LOCK=new Object();
    public static  synchronized void testSync4(){
        println(Thread.currentThread().getName()+"\t staticCount1="+ staticCount++ );
    }

    public static void testSync5(){
        synchronized (LOCK){
            println(Thread.currentThread().getName()+"\t staticCount2="+ staticCount++ );
        }
    }


    public static void testSync6(){
        synchronized (Test_02.class){
            println(Thread.currentThread().getName()+"\t staticCount2="+ staticCount++ );
        }
    }
    public static Random random=new Random();
    public static void main(String[] args){
        Runnable r1= () -> {
            for (int i = 0; i <50; i++) {
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Test_02.testSync4();
            }

        };
        Runnable r2= () -> {
            for (int i = 0; i <50 ; i++) {
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Test_02.testSync5();
            }

        };

        Runnable r3= () -> {
            for (int i = 0; i <50 ; i++) {
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Test_02.testSync6();
            }

        };

        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r3).start();
    }
}
