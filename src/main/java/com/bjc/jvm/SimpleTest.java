package com.bjc.jvm;

/**
 * @AUTHOR: 小于
 * @DATE: [2020/4/8  21:42]
 * @DESC:
 */
public class SimpleTest {


    public static void main(String[] args) {
        query(5,6);

    }

    protected static int query(int a,int b) {
          long flag = 1;
          int c= a+b;
          c+=flag;
          return c;
    }
}
/**
 * // class version 52.0 (52)
 * // access flags 0x21
 * public class com/bjc/jvm/SimpleTest {
 *
 *   // compiled from: SimpleTest.java
 *
 *   // access flags 0x1
 *   public <init>()V
 *    L0
 *     LINENUMBER 8 L0
 *     ALOAD 0
 *     INVOKESPECIAL java/lang/Object.<init> ()V
 *     RETURN
 *    L1
 *     LOCALVARIABLE this Lcom/bjc/jvm/SimpleTest; L0 L1 0
 *     MAXSTACK = 1
 *     MAXLOCALS = 1
 *
 *   // access flags 0x9
 *   public static main([Ljava/lang/String;)V
 *    L0
 *     LINENUMBER 12 L0
 *     ICONST_5   //将一个常量int 5 送至栈顶
 *     BIPUSH 6  // 将一个byte型常量值推送至栈顶
 *     INVOKESTATIC com/bjc/jvm/SimpleTest.query (II)I  // 调用query 方法 弹栈
 *     POP
 *    L1
 *     LINENUMBER 14 L1
 *     RETURN  // 方法出口
 *    L2
 *     LOCALVARIABLE args [Ljava/lang/String; L0 L2 0  // 具备变量表
 *     MAXSTACK = 2
 *     MAXLOCALS = 1
 *
 *   // access flags 0xA
 *   private static query(II)I
 *    L0
 *     LINENUMBER 17 L0
 *     ICONST_1
 *     ISTORE 2
 *    L1
 *     LINENUMBER 18 L1
 *     ILOAD 0
 *     ILOAD 1
 *     IADD
 *     ISTORE 3
 *    L2
 *     LINENUMBER 19 L2
 *     ILOAD 3
 *     ILOAD 2
 *     IADD
 *     ISTORE 3
 *    L3
 *     LINENUMBER 20 L3
 *     ILOAD 3
 *     IRETURN
 *    // 局部变量表
 *    L4
 *     LOCALVARIABLE a I L0 L4 0
 *     LOCALVARIABLE b I L0 L4 1
 *     LOCALVARIABLE flag I L1 L4 2
 *     LOCALVARIABLE c I L2 L4 3
 *     MAXSTACK = 2
 *     MAXLOCALS = 4
 * }
 */