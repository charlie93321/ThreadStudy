package com.bjc;

import java.util.concurrent.locks.LockSupport;

/**
 * @AUTHOR: 小于
 * @DATE: [2020/3/27  1:03]
 * @DESC: 线程的创建
 * <p>
 * <p>
 * 线程有创建 就绪 运行 阻塞 凋亡五种状态
 * <p>
 * <p>
 * <p>
 * public enum State {
 * <p>
 * * Thread state for a thread which has not yet started.
 * <p>
 * NEW, 线程刚刚创建还未调用start方法的时候所处的状态
 * <p>
 * <p>
 * * Thread state for a runnable thread.  A thread in the runnable
 * * state is executing in the Java virtual machine but it may
 * * be waiting for other resources from the operating system
 * * such as processor.
 * <p>
 * RUNNABLE,
 * <p>
 * <p>
 * * Thread state for a thread blocked waiting for a monitor lock.
 * * A thread in the blocked state is waiting for a monitor lock
 * * to enter a synchronized block/method or
 * * reenter a synchronized block/method after calling
 * * {@link Object#wait() Object.wait}.
 * <p>
 * BLOCKED, 线程阻塞(等待锁)
 * <p>
 * <p>
 * * Thread state for a waiting thread.
 * * A thread is in the waiting state due to calling one of the
 * * following methods:
 * * <ul>
 * *   <li>{@link Object#wait() Object.wait} with no timeout</li>
 * *   <li>{@link #join() Thread.join} with no timeout</li>
 * *   <li>{@link LockSupport#park() LockSupport.park}</li>
 * * </ul>
 * *
 * * <p>A thread in the waiting state is waiting for another thread to
 * * perform a particular action.
 * *
 * * For example, a thread that has called <tt>Object.wait()</tt>
 * * on an object is waiting for another thread to call
 * * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
 * * that object. A thread that has called <tt>Thread.join()</tt>
 * * is waiting for a specified thread to terminate.
 * <p>
 * WAITING, 线程处于等待状态,当调用这些方法时:Object.wait() Thread.join() Thread.park()
 * <p>
 * <p>
 * * Thread state for a waiting thread with a specified waiting time.
 * * A thread is in the timed waiting state due to calling one of
 * * the following methods with a specified positive waiting time:
 * * <ul>
 * *   <li>{@link #sleep Thread.sleep}</li>
 * *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
 * *   <li>{@link #join(long) Thread.join} with timeout</li>
 * *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
 * *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
 * * </ul>
 * <p>
 * TIMED_WAITING,线程处于指定时长的等待状态,当调用这些方法时:Thread.sleep(long) , Object.wait(long)  Thread.join(long) Thread.
 * <p>
 * <p>
 * * Thread state for a terminated thread.
 * * The thread has completed execution.
 * <p>
 * TERMINATED;
 * }
 */

public class A线程的创建和生命周期 {


    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread("线程1") {
            // run 状态
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        //Thread.sleep(1000);
                        int j = 0;
                        while (j < 100000) {
                            j++;
                            printThread(this, "1");
                        }
                        printThread(this, "1");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        printThread(t1, "main");

        new Thread() {
            State state = State.NEW;

            @Override
            public void run() {
                while (!t1.getState().equals(State.TERMINATED)) {
                    try {
                        Thread.sleep(1);
                        if (!t1.getState().equals(state)) {
                            printThread(t1, "2");
                            state = t1.getState();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        t1.start();


    }

    public static void printThread(Thread t1, String people) {
        System.out.println(t1.getName() + " >>>" + people + ">>>:" + t1.getState());
    }

}
