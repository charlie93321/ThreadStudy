package com.wt.threads;
import static com.wt.utils.Print.println;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/2  17:09]
 * @DESC: synchronized 关键字
 * 1. 锁的关键字
 * 2. 锁的重入
 *
 */
public class Test_01 {

    private int count=0;
    private Object lock=new Object();


    public void testSync1(){
        synchronized (lock){
            println(Thread.currentThread().getName()+"\t count="+ count++ );
        }
    }

    public   void testSync2(){
        synchronized (this){
            println(Thread.currentThread().getName()+"\t count="+ count++ );
        }
    }

    public synchronized void testSync3(){
        println(Thread.currentThread().getName()+"\t count="+ count++ );
    }

    public static void main(String[] args){

    }
}
