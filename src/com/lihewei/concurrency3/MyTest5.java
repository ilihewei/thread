package com.lihewei.concurrency3;

/**
 * @author lihewei
 *
 * 锁粗化
 * jit编译器在执行动态编译时，若发现前后相邻的syn块使用的是同一个锁对象，
 * 那么它就会把这几个syn块合并为一个较大的同步快，这样做的好处处于线程在执行代码时，
 * 就无需频繁申请与释放锁了，从而达到申请与释放锁一次，就可以执行完全部的同步代码块，从而提升了性能
 *
 */
public class MyTest5 {

    private  Object object=new Object();

    public  void method(){

      //  Object object=new Object();
        synchronized (object) {
            System.out.println(" hello world");
        }
        synchronized (object){
            System.out.println("welcome");
        }
        synchronized (object){
            System.out.println("person");
        }
    }
}
