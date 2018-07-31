package com.hbgj.lines;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread stop方法过于粗放,资源立即释放导致数据丢失或毁坏
 * Thread interrupt
 *
 Thread.currentThread.interrupt() 只对阻塞线程起作用，

 当线程阻塞时，调用interrupt方法后，该线程会得到一个interrupt异常，可以通过对该异常的处理而退出线程

 对于正在运行的线程，没有任何作用！
 * 如果当前的线程被阻塞( sleep , wait , join) 则会抛出 异常 ，并将状态修改为非中断状态
 *
 *如果当前线程被中断后再执行nio的操作就会抛出异常
 *
 *
 */
public class TestThreadStop {
    private static User user=new User("10001","tom");

    public static void main(String[] args){

        Runnable read_thread=new Runnable() {

            public void run() {
                while( true ){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(user);
                }
            }
        };
        Runnable write_thread=new Runnable() {
            @Override
            public synchronized void run() {
                user.setId("10002");
                //Thread.currentThread().stop();
                /**
                 *
                 *    * <p> If this thread is blocked in an invocation of the {@link
                 * Object#wait() wait()}, {@link Object#wait(long) wait(long)}, or {@link
                 * Object#wait(long, int) wait(long, int)} methods of the {@link Object}
                 * class, or of the {@link #join()}, {@link #join(long)}, {@link
                 * #join(long, int)}, {@link #sleep(long)}, or {@link #sleep(long, int)},
                 * methods of this class, then its interrupt status will be cleared and it
                 * will receive an {@link InterruptedException}.
                 * 线程被中断后
                 *
                 * 如果当前的线程被阻塞( sleep , wait , join) 则会抛出 异常 ，并将状态修改为非中断状态
                 *
                 *如果当前线程被中断后再执行nio的操作就会抛出异常
                 *
                 */
                Thread.currentThread().interrupt();
                user.setName("jerry");
            }
        };


        int thread_num=2;
        ExecutorService service= Executors.newFixedThreadPool(thread_num);

        service.execute(read_thread);
        service.execute(write_thread);

        service.shutdown();


    }

}
