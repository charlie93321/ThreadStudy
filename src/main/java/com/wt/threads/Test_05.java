package com.wt.threads;

import java.util.concurrent.TimeUnit;

import static com.wt.utils.Print.println;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/2  18:23]
 * @DESC:
 */
public class Test_05 {
    private volatile  double d=0.0d;

    public synchronized  void m1(double d){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("赋值");
        this.d=d;
    }

    public double m2(){
        return this.d;
    }

    public static void main(String[] args){
        Test_05 test05=new Test_05();

        Runnable r1=()->{
            test05.m1(10);
        };

        Runnable r2=()->{
            println("d==>"+test05.m2());
        };


        new Thread(r1).start();
        new Thread(r2).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r2).start();
    }
}
