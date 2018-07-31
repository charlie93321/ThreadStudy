package com.hbgj.lines;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * object.wait/object.notify 不是随便可以调用的
 * 必须在使用synchronize 关键字后使用, 换句话说 必须处于拥有锁的状态才能使用
 *
 *
 *
 */
public class WaitAndNotify {
    private static  List<String> commonList=new ArrayList<String>();
    private static  Object obj=new Object();
    private static AtomicLong count=new AtomicLong(0l);
    private static  final Integer FULL=2;
    private static  Integer size_of_list=0;
    public static void main(String[] args){

        Runnable  putList=new Runnable() {
            @Override
            public void run() {

                synchronized (obj){

                    while(true) {
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (size_of_list==FULL.intValue()) {
                            try {
                                obj.notify();
                                obj.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                             commonList.add("index-"+count.addAndGet(1));
                            size_of_list++;
                        }
                    }
                }
            }
        };

        Runnable popList=new Runnable() {
            @Override
            public void run() {
                synchronized (obj){

                    while(true) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (size_of_list==0) {
                            try {
                                obj.notify();
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {


                            String e=commonList.remove(size_of_list-1);
                            System.out.println("remove -> e :"+e);

                            size_of_list--;
                        }
                    }
                }
            }
        };





        int total=2;
      /*  ExecutorService service= Executors.newFixedThreadPool(total);

        service.execute(popList);
        service.execute(putList);




        service.shutdown();*/


      Thread t1=new Thread(popList);
      Thread t2=new Thread(putList);


      t1.start();
      t2.start();



    }


}
