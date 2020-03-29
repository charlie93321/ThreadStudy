package com.bjc.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR: 小于
 * @DATE: [2020/3/29  21:29]
 * @DESC: -Xmx
 * A.堆 定义: 由new关键字开辟的内存空间 使用的是堆内存 可以使用 -Xmx1024m 来设置
 * 特点:    1. 垃圾自动回收
 * 2.  线程共享的内存区域,需要考虑线程安全问题
 * B. 堆内存溢出: 由所有new关键字开辟的内存空间之和大于了-Xmx设置的堆内存大小
 * C. 堆内存诊断
 * 1. jps工具
 * 查看当前系统中有哪些java进程
 * 2. jmap工具
 * 查看堆内存的占用情况
 * 3. jconsole
 * 图形界面多功能监测工具,可以连续监测
 */
public class B堆 {


    public static void main(String[] args) throws InterruptedException {
        // 1. 堆内存溢出
        //heapOver();
        // 2. 堆内存诊断
        diagnosisHeap();

    }


    /**
     * 设置堆内存-Xmx128m
     * 使用 jmap 诊断堆内存  jdk8 jmap -heap pid
     */
    public static void diagnosisHeap() throws InterruptedException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>0");
        Thread.sleep(30 * 1000);
        // 申请10MB的内存空间
        byte[] arr = new byte[1024 * 1024 * 10];
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>1");
        Thread.sleep(30 * 1000);
        arr = null;
        System.gc();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>2");
        Thread.sleep(3000 * 1000);

    }

    /**
     * Microsoft Windows [版本 10.0.18363.720]
     * (c) 2019 Microsoft Corporation。保留所有权利。
     *
     * C:\Users\01>jps
     * 17360 NailgunRunner
     * 15572 Jps
     * 12792 Launcher
     * 5640 B堆
     * 4076
     *
     * C:\Users\01>jmap -heap 5640
     * Attaching to process ID 5640, please wait...
     * Debugger attached successfully.
     * Server compiler detected.
     * JVM version is 25.161-b14
     *
     * using thread-local object allocation.
     * Parallel GC with 4 thread(s)
     *
     * Heap Configuration:
     *    MinHeapFreeRatio         = 0
     *    MaxHeapFreeRatio         = 100
     *    MaxHeapSize              = 134217728 (128.0MB)
     *    NewSize                  = 44564480 (42.5MB)
     *    MaxNewSize               = 44564480 (42.5MB)
     *    OldSize                  = 89653248 (85.5MB)
     *    NewRatio                 = 2
     *    SurvivorRatio            = 8
     *    MetaspaceSize            = 21807104 (20.796875MB)
     *    CompressedClassSpaceSize = 1073741824 (1024.0MB)
     *    MaxMetaspaceSize         = 17592186044415 MB
     *    G1HeapRegionSize         = 0 (0.0MB)
     *
     * Heap Usage:
     * PS Young Generation
     * Eden Space:
     *    capacity = 34078720 (32.5MB)
     *    used     = 2782136 (2.6532516479492188MB)
     *    free     = 31296584 (29.84674835205078MB)
     *    8.163851224459135% used
     * From Space:
     *    capacity = 5242880 (5.0MB)
     *    used     = 0 (0.0MB)
     *    free     = 5242880 (5.0MB)
     *    0.0% used
     * To Space:
     *    capacity = 5242880 (5.0MB)
     *    used     = 0 (0.0MB)
     *    free     = 5242880 (5.0MB)
     *    0.0% used
     * PS Old Generation
     *    capacity = 89653248 (85.5MB)
     *    used     = 0 (0.0MB)
     *    free     = 89653248 (85.5MB)
     *    0.0% used
     *
     * 1740 interned Strings occupying 155784 bytes.
     *
     * C:\Users\01>jmap -heap 5640
     * Attaching to process ID 5640, please wait...
     * Debugger attached successfully.
     * Server compiler detected.
     * JVM version is 25.161-b14
     *
     * using thread-local object allocation.
     * Parallel GC with 4 thread(s)
     *
     * Heap Configuration:
     *    MinHeapFreeRatio         = 0
     *    MaxHeapFreeRatio         = 100
     *    MaxHeapSize              = 134217728 (128.0MB)
     *    NewSize                  = 44564480 (42.5MB)
     *    MaxNewSize               = 44564480 (42.5MB)
     *    OldSize                  = 89653248 (85.5MB)
     *    NewRatio                 = 2
     *    SurvivorRatio            = 8
     *    MetaspaceSize            = 21807104 (20.796875MB)
     *    CompressedClassSpaceSize = 1073741824 (1024.0MB)
     *    MaxMetaspaceSize         = 17592186044415 MB
     *    G1HeapRegionSize         = 0 (0.0MB)
     *
     * Heap Usage:
     * PS Young Generation
     * Eden Space:
     *    capacity = 34078720 (32.5MB)
     *    used     = 13267912 (12.653266906738281MB)
     *    free     = 20810808 (19.84673309326172MB)
     *    38.9331289438101% used
     * From Space:
     *    capacity = 5242880 (5.0MB)
     *    used     = 0 (0.0MB)
     *    free     = 5242880 (5.0MB)
     *    0.0% used
     * To Space:
     *    capacity = 5242880 (5.0MB)
     *    used     = 0 (0.0MB)
     *    free     = 5242880 (5.0MB)
     *    0.0% used
     * PS Old Generation
     *    capacity = 89653248 (85.5MB)
     *    used     = 0 (0.0MB)
     *    free     = 89653248 (85.5MB)
     *    0.0% used
     *
     * 1741 interned Strings occupying 155888 bytes.
     *
     * C:\Users\01>jmap -heap 5640
     * Attaching to process ID 5640, please wait...
     * Debugger attached successfully.
     * Server compiler detected.
     * JVM version is 25.161-b14
     *
     * using thread-local object allocation.
     * Parallel GC with 4 thread(s)
     *
     * Heap Configuration:
     *    MinHeapFreeRatio         = 0
     *    MaxHeapFreeRatio         = 100
     *    MaxHeapSize              = 134217728 (128.0MB)
     *    NewSize                  = 44564480 (42.5MB)
     *    MaxNewSize               = 44564480 (42.5MB)
     *    OldSize                  = 89653248 (85.5MB)
     *    NewRatio                 = 2
     *    SurvivorRatio            = 8
     *    MetaspaceSize            = 21807104 (20.796875MB)
     *    CompressedClassSpaceSize = 1073741824 (1024.0MB)
     *    MaxMetaspaceSize         = 17592186044415 MB
     *    G1HeapRegionSize         = 0 (0.0MB)
     *
     * Heap Usage:
     * PS Young Generation
     * Eden Space:
     *    capacity = 34078720 (32.5MB)
     *    used     = 681592 (0.6500167846679688MB)
     *    free     = 33397128 (31.84998321533203MB)
     *    2.0000516451322117% used
     * From Space:
     *    capacity = 5242880 (5.0MB)
     *    used     = 0 (0.0MB)
     *    free     = 5242880 (5.0MB)
     *    0.0% used
     * To Space:
     *    capacity = 5242880 (5.0MB)
     *    used     = 0 (0.0MB)
     *    free     = 5242880 (5.0MB)
     *    0.0% used
     * PS Old Generation
     *    capacity = 89653248 (85.5MB)
     *    used     = 771688 (0.7359390258789062MB)
     *    free     = 88881560 (84.7640609741211MB)
     *    0.8607473986887793% used
     *
     * 1727 interned Strings occupying 154952 bytes.
     *
     * C:\Users\01>
     */


    /**
     * 堆内存溢出举例
     * 1. 设置 -Xmx8m 2. 运行下面的方法     i=16
     * 1. 设置 -Xmx1024m 2. 运行下面的方法  i=23
     * 可以看到是由于堆内存被耗尽而溢出
     */

    public static void heapOver() {
        String a = "hello world";
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {

                list.add(a);
                a += a;
                i++;

            }
        } catch (Throwable e) {
            System.out.println("此时i的大小>>>>>>>:" + i);
            e.printStackTrace();

        }

    }

}
