package com.bjc.jvm;

/**
 * @AUTHOR: 小于
 * @DATE: [2020/3/29  13:30]
 * @DESC:
 *
 *
 * 栈内存溢出
 * 1. 栈幁 过多
 * 2. 栈幁 过大
 */
public class A虚拟机栈 {


    public static void main(String[] args) {
        int rs = method1();

    }


    public static int method1() {
        int a = 10;
        int b = 12;
        return method2(a, b);
    }

    private static int method2(int a, int b) {
        int c = 0;
        c = a + b;
        return c;
    }









}
