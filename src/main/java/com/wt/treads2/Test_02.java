package com.wt.treads2;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/20  11:29]
 * @DESC: 重入锁
 */
public class Test_02 {
    /**
     *  默认 或者 设置 false  为非公平 锁
     *  公平锁内置等待队列,根据队列 调度线程
     */
    private static  ReentrantLock lock=new ReentrantLock(false);


    public static void main(String[] args) throws InterruptedException {

         List<String> container=new ArrayList<>();
         List<String> safeContainer=Collections.synchronizedList(new ArrayList<>());
         AtomicInteger count=new AtomicInteger(0);
        int num=50;
        int numThread=800;
        CountDownLatch latch=new CountDownLatch(numThread);
        for (int j = 0; j <numThread ; j++) {
            new Thread(()->{
                for (int i = 0; i <num ; i++) {
                     lock.lock();
                     try {
                         container.add(Thread.currentThread().getName() + "_" + i);
                     }catch (Exception e){
                         e.printStackTrace();
                     }finally {
                         lock.unlock();
                     }

                    safeContainer.add(Thread.currentThread().getName()+"_"+i);
                    count.incrementAndGet();
                }
                latch.countDown();
            },""+j).start();
        }

        latch.await();

        System.out.println("the size of list is ==>"+container.size() +"== the add times is "+ count.get());
        System.out.println("the size of safe list is ==>"+safeContainer.size() +"== the add times is "+ count.get());

    }


    @Test
    public void test2(){
        String lines=" 0_0, 0_1, 0_2, 0_3, 0_4, 1_0, 1_1, 1_2, 1_3, 1_4, 2_0, 2_1, 2_2, 2_3, 2_4, 3_0, 3_1, 3_2, 3_3, 3_4, 5_0, 5_1, 5_2, 5_3, 5_4, 6_0, 6_1, 6_2, 6_3, 6_4, 7_0, 7_1, 7_2, 7_3, 7_4, 8_0, 8_1, 8_2, 8_3, 8_4, 9_0, 9_1, 9_2, 9_3, 9_4, 10_0, 10_1, 10_2, 10_3, 10_4, 11_0, 11_1, 11_2, 11_3, 11_4, 12_0, 12_1, 12_2, 12_3, 12_4, 13_0, 13_1, 13_2, 13_3, 13_4, 14_0, 14_1, 14_2, 14_3, 14_4, 15_0, 15_1, 15_2, 15_3, 15_4, 4_0, 4_1, 4_2, 4_3, 4_4, 16_0, 16_1, 16_2, 16_3, 16_4, 17_0, 17_1, 17_2, 17_3, 17_4, 18_0, 18_1, 18_2, 18_3, 18_4, 19_0, 19_1, 19_2, 19_3, 19_4, 20_0, 20_1, 20_2, 20_3, 20_4, 21_0, 21_1, 21_2, 21_3, 21_4, 22_0, 22_1, 22_2, 22_3, 22_4, 23_0, 23_1, 23_2, 23_3, 23_4, 24_0, 24_1, 24_2, 24_3, 24_4, 25_0, 25_1, 25_2, 25_3, 25_4, 26_0, 26_1, 26_2, 26_3, 26_4, 27_0, 27_1, 27_2, 27_3, 27_4, 28_0, 28_1, 28_2, 28_3, 28_4, 29_0, 29_1, 29_2, 29_3, 29_4, 30_0, 30_1, 30_2, 30_3, 30_4, 31_0, 31_1, 31_2, 31_3, 31_4, 32_0, 32_1, 32_2, 32_3, 32_4, 33_0, 33_1, 33_2, 33_3, 33_4, 34_0, 34_1, 34_2, 34_3, 34_4, 35_0, 35_1, 35_2, 35_3, 35_4, 36_0, 36_1, 36_2, 36_3, 36_4, 37_0, 37_1, 37_2, 37_3, 37_4, 38_0, 38_1, 38_2, 38_3, 38_4, 39_0, 39_1, 39_2, 39_3, 39_4, 40_0, 40_1, 40_2, 40_3, 40_4, 41_0, 41_1, 41_2, 41_3, 41_4, 42_0, 42_1, 42_2, 42_3, 42_4, 79_0, 79_1, 79_2, 78_0, 78_1, 79_4, 78_2, 78_3, 78_4, 77_0, 77_1, 77_2, 77_3, 77_4, 76_0, 76_1, 76_2, 76_3, 76_4, 72_0, 72_1, 72_2, 72_3, 72_4, 74_0, 74_1, 74_2, 74_3, 74_4, 75_0, 75_1, 75_2, 75_3, 75_4, 73_0, 73_1, 73_2, 73_3, 73_4, 70_0, 71_0, 70_1, 70_2, 71_1, 70_3, 71_2, 70_4, 71_3, 71_4, 69_0, 69_1, 69_2, 69_3, 69_4, 68_0, 68_1, 68_2, 68_3, 68_4, 66_0, 66_1, 66_2, 66_3, 66_4, 67_0, 67_1, 67_2, 67_3, 67_4, 65_0, 65_1, 65_2, 65_3, 65_4, 64_0, 64_1, 64_2, 64_3, 64_4, 63_0, 63_1, 63_2, 63_3, 63_4, 61_0, 61_1, 61_2, 61_3, 61_4, 62_0, 62_1, 62_2, 62_3, 62_4, 59_0, 59_1, 59_2, 59_3, 59_4, 60_0, 60_1, 60_2, 60_3, 60_4, 57_0, 57_1, 57_2, 57_3, 57_4, 58_0, 58_1, 56_0, 58_2, 56_1, 58_3, 56_2, 56_3, 58_4, 56_4, 55_0, 55_1, 55_2, 55_3, 55_4, 54_0, 54_1, 54_2, 54_3, 54_4, 53_0, 53_1, 53_2, 53_3, 53_4, 52_0, 52_1, 52_2, 52_3, 52_4, 51_0, 51_1, 51_2, 51_3, 51_4, 49_0, 49_1, 49_2, 49_3, 49_4, 48_0, 48_1, 48_2, 48_3, 48_4, 46_0, 46_1, 46_2, 46_3, 46_4, 45_0, 45_1, 45_2, 45_3, 45_4, 44_0, 44_1, 44_2, 44_3, 47_0, 44_4, 47_1, 47_2, 47_3, 47_4, 43_0, 43_1, 43_2, 43_3, 43_4, 50_0, 50_1, 50_2, 50_3, 50_4";

        CountMap countMap= new CountMap();
        for (String line : lines.split(",")) {
             countMap.put(line);
        }
        countMap.find();

    }

    class CountMap{
        private Map<String,List<Integer>> maps=new HashMap<>();

        public void put(String line){
            if(null==line || line.isEmpty()) return;
            String[] arr=line.trim().split("_");

            if(null!=arr && arr.length==2){
                 if(!maps.containsKey(arr[0])){
                     maps.put(arr[0],new ArrayList<>(5));
                 }
                maps.get(arr[0]).add(Integer.parseInt(arr[1]));
            }
        }

        public void find(){
            ArrayList<String> list=new ArrayList<>(maps.keySet());
            Collections.sort(list, Comparator.comparingInt(Integer::parseInt));
            for (String key : list ) {
                List<Integer> values=maps.get(key);
                /*if(values.size()!=5)*/System.out.println(key+"===>"+values);
            }
        }
    }
}
