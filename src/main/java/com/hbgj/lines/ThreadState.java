package com.hbgj.lines;

import com.hbgj.nio.BasicOperation;
import com.hbgj.socket.SocketServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 线程的几种状态
 *
 * 1.new 初始化 => new了线程，但是为调用start方法
 *
 * 2.running  包括就绪和运行两种状态 => 线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获取cpu 的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得cpu 时间片后变为运行中状态（running）
 *
 * 3.block 阻塞  锁机制
 *
 * 4.等待 超时等待   线程间的协调机制  notify  wait (底层应该也是锁机制决定的)
 *
 * 5 中止 阻断，就是指线程不意外不正常结束
 *
 * 6 结束  线程被销毁
 *
 *
 */
public class ThreadState {
  private static User user=new User("10001","tom");

  public static void main(String[] args) throws InterruptedException {






    Thread t1=new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.interrupted();


    System.out.println("-end-");







  }
}
