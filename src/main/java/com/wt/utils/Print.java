package com.wt.utils;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/2/2  17:11]
 * @DESC:
 */
public class Print {
    public static  void println(Object... args){
        if(args==null || args.length==0) return;

        for (Object arg : args) {
            System.out.println(arg);
        }
    }

    public static  void print(Object... args){
        if(args==null || args.length==0) return;

        for (Object arg : args) {
            System.out.print(arg+"\t");
        }
    }
}
