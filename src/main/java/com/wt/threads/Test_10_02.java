package com.wt.threads;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/16  13:35]
 * @DESC:
 */
public class Test_10_02 {
    private static Object lock=new Object();
    private static Test_10.Volum<Integer> vs=new Test_10.Volum<>(10);
    public static void main(String[] args){

        new Thread(() -> {
             synchronized (lock){
               //  while(true) {

                     if (vs.index != 5) {
                         System.out.println("wait===>" + vs.index);
                         try {
                             lock.wait();
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }

                     System.out.println("end===>" + vs.index);
                     lock.notifyAll();

                 }
             //}
        }).start();



        new Thread(() -> {
            synchronized (lock){
                for (int i = 0; i <10 ; i++) {
                    System.out.println("add==>"+vs.index);
                    vs.add(i);
                    if(vs.index==5) {
                        System.out.println("notify==>"+vs.index);
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();



    }
}
