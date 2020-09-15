package com.lihewei.concurrency3;

/**
 * @author lihewei
 *
 * 锁消除技术：
 *
 *编译器对于锁的优化措施：
 * jit编译器（just in time编译器）可以在动态编译同步代码时，使用一种叫做逃逸分析的技术，
 * 来通过该项技术判别程序中所使用的锁对象是否只被一个线程所使用，而没有散步到其他线程中，
 * 如果情况就是这样的话，那么jit编译器在编译这个同步代码时就不会生成syn关键字所标识的锁
 * 的申请与释放机器码，从而消除了锁的使用流程。
 *
 *
 */
public class MyTest4 {

//  private  Object object=new Object();

    public void method(){
        Object object=new Object();
        synchronized (object){
            System.out.println("hello world");
        }
    }
}
