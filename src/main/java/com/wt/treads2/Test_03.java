package com.wt.treads2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/20  22:24]
 * @DESC: 线程安全容器  synchronize 实现
 */
public class Test_03 {

    static class SynSafeContainer<T>{

        private Object[] container;

        private final Integer DEFAULT_CAPACITY=5;

        private final Integer FULL_CAPACITY=10;

        private volatile  Integer index=0;

        public SynSafeContainer(){
             this.container=new Object[DEFAULT_CAPACITY];
        }

        public SynSafeContainer(Integer capacity){
            if(capacity>FULL_CAPACITY){
                capacity=FULL_CAPACITY;
            }
            this.container=new Object[capacity];
        }


        public synchronized  void add(T t){
            if(null==t)return;
            while(index==FULL_CAPACITY){
                //this.notifyAll();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(index>=container.length){
                int newCapacity=getNewCapacity(container.length);
                Object[] newArray=new Object[newCapacity];
                System.arraycopy(container,0,newArray,0,container.length);
                container=newArray;
            }
            container[index++]=t;
            this.notifyAll();
            //System.out.println("xxx"+container.length);
        }

        public synchronized  T remove(){
            T element=null;

            while (container.length==0){
                this.notifyAll();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(container.length==1) {
                element= (T) container[0];
                container = new Object[container.length-1];
            }else{
                element= (T) container[0];
                Object[] newArray = new Object[container.length-1];
                System.arraycopy(container,1,newArray,0,newArray.length);
                container=newArray;
            }
            System.out.println("the size of container is "+container.length);
            index--;
            return  element;
        }


        private Integer getNewCapacity(int length) {
            int newCapacity=length+1;

            if(newCapacity>FULL_CAPACITY){
                return FULL_CAPACITY;
            }

            return newCapacity;

        }

    }



     public static void main(String[] args) throws InterruptedException {
         SynSafeContainer<String> container=new SynSafeContainer<>(2);
         int numThread=3;
         int num=100;
         AtomicInteger count=new AtomicInteger(0);
         CountDownLatch latch=new CountDownLatch(numThread+1);
         for (int i = 0; i <numThread; i++) {
             new Thread(()->{
                 for (int j = 0; j <num; j++) {
                     container.add(Thread.currentThread().getName()+"_"+j);
                 }
                 latch.countDown();
                 count.incrementAndGet();
             },"线程"+i).start();
         }
         Thread[]  readThreads=new Thread[numThread-1];
         for (int i = 0; i <numThread-1; i++) {
             readThreads[i]=new Thread(()->{
                 String ele=container.remove();
                 while(true){
                     if(ele!=null) {
                         System.out.println("poll===>" + Arrays.toString(container.container) + "====>" + ele);

                     }else {
                         try {
                             Thread.sleep(1);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                     ele = container.remove();
                 }
             },"线程read");
            // readThreads[i].setDaemon(true);
             readThreads[i].start();
         }


         new Thread(()->{
             while(true) {
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 if (count.get() == numThread) {
                     if(container.container.length==0){
                         for (Thread readThread : readThreads) {
                               readThread.stop();
                         }
                         break;
                     }

                 }
             }
             latch.countDown();
         }).start();

         latch.await();

         System.out.println("final===>"+ Arrays.toString(container.container));




     }





}
