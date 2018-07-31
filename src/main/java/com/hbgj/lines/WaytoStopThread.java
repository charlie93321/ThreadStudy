package com.hbgj.lines;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 当我们想停止一个线程，又不想像stop方法那样粗暴，怎么办了，使用标志符号就行
 *
 *
 *
 */
public class WaytoStopThread {
    private static User user=new User("10001","tom");

    public static void main(String[] args){

        Runnable read_thread=new Runnable() {

            public  void run() {
                while( true ){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(user);
                }
            }
        };

        int thread_num=2;
        ExecutorService service= Executors.newFixedThreadPool(thread_num);

        service.execute(read_thread);
        service.execute(new ThreadCustomer());

        service.shutdown();


    }


    static class ThreadCustomer extends Thread implements  Runnable{

        @Override
        public synchronized  void run() {
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (stopMe) return;
                user.setId("10003");
                this.setStopMe(true);
                user.setName("charlie");
            }
        }

        private boolean stopMe;

        public boolean isStopMe() {
            return stopMe;
        }

        public void setStopMe(boolean stopMe) {
            this.stopMe = stopMe;
        }


    }
}
