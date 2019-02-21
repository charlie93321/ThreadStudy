package com.wt.threads;

import java.util.Arrays;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/14  20:03]
 * @DESC:
 */
public class Test_10 {

    public static class Volum<T>{
        public Object[] args;
        public Integer capacity=3;
        public  volatile  Integer index=0;
        public Volum(int capacity){
            if(capacity>0)
                 this.capacity=capacity;

            args=new Object[capacity];
        }


        public void add(T t){
            if(null!=t && index<capacity){
                args[index++]=t;
            }else{
                int oldCapacity=this.capacity;
                this.capacity=oldCapacity*2+1;
                Object[] args2=new Object[capacity];
                System.arraycopy(args,0,args2,0,oldCapacity);
                args=args2;
                add(t);
            }
        }




    }

    public static void main(String[] args){
       final Volum<Integer> vs=new Volum<>(5);

        Runnable r1 = () -> {
            for (int i = 0; i <10; i++) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                vs.add(i);
            }
        };

        Runnable r2 = () -> {
            while(true) {
                try {
                    Thread.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(vs.index==5) {
                    System.out.println("end");
                    break;
                }else
                    System.out.println("......."+vs.index);
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();

        System.out.println(Arrays.toString(vs.args));

    }
}
