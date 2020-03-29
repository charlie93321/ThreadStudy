package com.bjc;

/**
 * @AUTHOR: 小于
 * @DATE: [2020/3/28  21:14]
 * @DESC:
 */
public class C线程的wait和notify {


    /**
     * wait 和 notify 方法是Object类就有的,因而所用的对象都可以调用,这句话不完全对,只有当这个对象是同步代码块的锁时,这次调用才有效用
     * Object lock = new Object();               lock.wait() 方法的作用是释放锁lock,并将当前线程push进入对象lock的等待队列
     * lock.notify() 方法的作用是在等待队列中随机选择一个线程唤醒,这个选择是不公平的,不是先等待的线程就会被优先选择,这个选择是完全随机的
     * 被唤醒的线程,会尝试去获取锁,直到获得锁lock 进入就绪状态 等待cpu的调度
     */

    private static final Object LOCK = new Object();


    public static void main(String[] args) {


        Thread t1=new Thread("线程一") {
            @Override
            public void run() {

                synchronized (LOCK){
                    for (int i = 0; i < 2; i++) {
                        System.out.println("<<<<<<<<<<<<<"+i);
                        try {
                              LOCK.notifyAll();
                              LOCK.wait();
                              //System.out.println(">>>>>>>>>>"+i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // LOCK.notifyAll();
                    
                }

            }

        };t1.start();

        Thread t2 =new Thread("线程二") {
            @Override
            public void run() {

                synchronized (LOCK){
                    for (int i = 0; i < 2; i++) {
                        System.out.println("*******************"+i);
                        LOCK.notifyAll();
                        try {
                            LOCK.wait();

                            //System.out.println("-*-*-*-*-*-*-*-*-*"+i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        };t2.start();




    }






}
