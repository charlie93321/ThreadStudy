package com.wt.threads;

import static com.wt.utils.Print.print;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/14  19:46]
 * @DESC:
 */
public class Test_09 {
    /*volatile*/  Boolean b=true;
    void m(){
        print("start");
        int i=10000;
        while(b){
            /*try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
           /* if((i++)% 123543534==0)
                            print("xxxx");*/

        }
        print("end");
    }
    public static void main(String[] args){
        Test_09 t=new Test_09();
        Runnable r= () -> t.m();

        Runnable r2= () -> {
            while(true)
                print("print==>"+t.b);
        };

        new Thread(r).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.b=false;
    }
}
