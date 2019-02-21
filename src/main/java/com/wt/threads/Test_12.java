package com.wt.threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/16  22:13]
 * @DESC:
 */
public class Test_12<T> {

    private LinkedList<T> container=new LinkedList<T>();
    private final Integer MAX=3;
    private Integer count=0;
    public synchronized  void put(T t) throws InterruptedException {
        if(null==t)return;
        while (count>=MAX){
            this.wait();
        }
        container.add(t);
        count++;
        this.notifyAll();
    }
    public synchronized  T get() throws InterruptedException {
        T re=null;
        while(count==0){
            this.notifyAll();
            this.wait();
        }
        System.out.println(container);
        re=container.removeFirst();
        count--;
        return re;
    }

    public static void main(String[] args){
        Test_12<Integer> cs=new Test_12<>();
        for (int i = 0; i <2 ; i++) {
            new Thread(() -> {
                while(true){
                    try {
                       // Thread.sleep(1000);
                        String name=Thread.currentThread().getName();
                        System.out.println(name+"==>"+cs.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"getThread-"+i).start();
        }


        for (int i = 0; i <50 ; i++) {
            new Thread(() -> {
                for (int j = 0; j <25 ; j++) {

                    try {
                       // Thread.sleep(10);
                        String name=Thread.currentThread().getName();
                        int threadI= Integer.parseInt(name.split("-")[1]);
                        cs.put((threadI*100+1)*(j+1)*10);
                        System.out.println(name+"==>|"+((threadI+1)*j));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"putThread-"+i).start();
        }
    }

}
