package com.hbgj.lines;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时 线程调度器
 *
 *
 *
 *
 */
public class CountLauchTest {


   private static final Integer  countDown=5;
   private static CountDownLatch latch=new CountDownLatch(countDown);
   private static Random random=new Random();
   static{
       random.setSeed(System.currentTimeMillis());
   }
   public static void main(String[] args) throws InterruptedException {


      Runnable runnable=new Runnable() {
         @Override
         public void run() {
            try {
               Thread.sleep(random.nextInt(1000)+1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

            System.out.println("I am working to ensure fire rock .......  ");

            latch.countDown();
         }
      };

      int  total=10;
      ExecutorService service= Executors.newFixedThreadPool(total);

      for (int i = 0; i < total; i++) {
         service.execute(runnable);
      }

      latch.await();
      System.out.println("fire rock ");
      service.shutdown();

   }



}
