package com.lihewei.concurrency7;

/**
 * threadlocal
 * 本质上，threadlocal是通过空间换时间，从而实现每个线程当中都会有一个变量的副本，这样的每个线程都会操作该副本，
 * 从而完全规避了多线程的并发问题
 * java中存在四种类型的引用：

 * 1，强引用（Strong）对象指向强引用，怎样也不会被垃圾回收掉
 * 2.软引用（soft） 只有当内存不够，才会回收
 * 3.弱引用（weak） 下次回收
 * 4.虚引用（phantom）
 *
 *
 * public  class Test{
 *     private static final ThreadLocal<String> t1=new ThreadLocal();
 *
 *     try{
 *     }finally{
 *     t1.remove();
 *    }
 *
 * @author lihewei
 */


public class MyTest3 {
    public static void main(String[] args) {

        ThreadLocal<String> threadLocal=new ThreadLocal<>();
        threadLocal.set("hello world");
        System.out.println(threadLocal.get());
        threadLocal.set("welcome");
        System.out.println(threadLocal.get());

    }
}
