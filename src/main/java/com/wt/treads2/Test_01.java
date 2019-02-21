package com.wt.treads2;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/20  8:52]
 * @DESC:  容器 -- 可测量(线程安全)
 *
 *   生产者   向 容器中 加入 元素
 *
 *   测量者   测量 容器中 的元素个数  , 当元素个数达到5 时 打印 并退出该线程
 *
 *
 *
 *
 */
public class Test_01 {

    private  static  final Integer BASIC_NUM=10;
    private static  List<Integer> list=new ArrayList<>();
    private static final  Object LOCK=new Object();
    private static   volatile boolean hasPrint=false;

    private static  Integer stopNum= 0;

    public static void main(String[] args){




         int pNum=7;


        new Thread(() -> {
            synchronized (LOCK) {
                while(true) {
                    System.out.println("线程二在执行..."+stopNum);

                    if(stopNum==pNum) {
                        System.out.println("list.size = " + BASIC_NUM + " ? " + list.size()+" stopNum="+stopNum);

                        System.out.println("线程二停止执行");
                        break;

                    }

                    while (list.size() % BASIC_NUM != 0 ) {
                        try {
                            System.out.println("c==>stopNum==>"+stopNum);
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("list.size = " + BASIC_NUM + " ? " + list.size()+" stopNum="+stopNum);
                    hasPrint = true;
                    LOCK.notifyAll();
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



        for (int j = 100; j <100+pNum ; j++) {
            new Thread(() -> {
                synchronized (LOCK) {
                    for (int i = 1; i <=31; i++) {
                        while(list.size()%BASIC_NUM==0  && !hasPrint ){
                            try {
                                System.out.println("p==>stopNum==>"+stopNum);
                                LOCK.notifyAll();
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        list.add(i+Integer.parseInt(Thread.currentThread().getName()));
                        System.out.println(
                                "add==>list==>"+(i+Integer.parseInt(Thread.currentThread().getName()))+" list.size="+list.size());
                        hasPrint=false;
                    }
                    stopNum++;
                    LOCK.notifyAll();
                }
            },""+j).start();
        }
    }
}
