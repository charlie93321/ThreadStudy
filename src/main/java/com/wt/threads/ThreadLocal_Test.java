package com.wt.threads;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/18  21:40]
 * @DESC: 对 threadLocal 对象 的使用 的解析
 */

public class ThreadLocal_Test {

    /**
     * @Auther: 小于
     * @Date: 2019/2/18 21:41
     * @Description:  什么是  threadLocal 对象
     *
     *  ThreadLocal  叫做 线程本地变量  , 为变量在每个线程中都创建一个副本 , 每个线程都可以访问自己内部的 副本变量
     *  [关于threadLocal的详细描述==>https://www.cnblogs.com/dolphin0520/p/3920407.html]
     *
     *
     *
     */
    @Test
    public void test_what(){

        ThreadLocal<Integer> tl1=new ThreadLocal<>();
        tl1.set(20);
        tl1.get();

        ThreadLocal<Integer> tl2=new ThreadLocal<>();
        tl2.set(30);
        tl2.get();


        List<ThreadLocal<Integer>> tls=new ArrayList<ThreadLocal<Integer>>();

        for (int i = 0; i <16 ; i++) {
            ThreadLocal<Integer> local=new ThreadLocal<>();
            local.set(31+i);
            tls.add(local);
        }

        for (ThreadLocal<Integer> tl : tls) {
            System.out.println(tl.get());
        }




    }

    class A{
        private String code;
        private String name;
        @Override
        public int hashCode() {
            return code.hashCode()+name.hashCode();
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return code.toString()+name.toString();
        }

    }
    @Test
    public void test2(){
       /* HashMap<A,String> map = new HashMap<A,String>();
        A a1 = new A();
        a1.setCode("123");
        a1.setName("456");
        map.put(a1, "test1");
        System.out.println(a1+"===>"+map);
        a1.setCode("789");
        map.put(a1, "test2");*/






       /* Set<Integer> hs=new HashSet<>();
        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
            Integer fv=hash(i) & 15;
            System.out.println("fv==>"+fv+", now value ===>"+i +"=fv==1==>"+(fv.equals(1)));
            if(hs.contains(fv)){
                System.out.println("fv==>"+fv+", now value ===>"+i+",hs==>"+hs);
                break;
            }else
                hs.add(fv);
        }*/

       HashMap<Integer,String> mk=new HashMap<>();

       mk.put(0,"xxx");
       mk.put(16,"yyy");

       System.out.println(mk);



    }



     final int hash(Object key) {
        int h= key.hashCode();
        System.out.println("h="+h+",h>>> 16==>"+(h>>> 16));
        return (key == null) ? 0 : h ^ (h >>> 16);
    }






}
