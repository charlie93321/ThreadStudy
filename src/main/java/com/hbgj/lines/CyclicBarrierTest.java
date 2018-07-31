package com.hbgj.lines;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    /**
     * run
     *
     *
     */
    static class BrrrierAction implements  Runnable{
        private int step;
        private int num;
        public BrrrierAction(int step,int num){
            this.step=step;
            this.num=num;
        }

        @Override
        public void run() {
            if(step==1){

            }
        }
    }
    public static void main(String[] args){

        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);


    }




}
