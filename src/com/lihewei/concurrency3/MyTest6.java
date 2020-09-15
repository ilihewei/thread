package com.lihewei.concurrency3;

/**
 * 死锁：线程1：等待线程2互斥持有的资源，而线程2也在等待线程1互斥持有的资源，两个线程都无法继续执行
 * 活锁： 线程持续重试一个总是失败的操作，导致无法继续执行
 * 饿死：线程一直被调度器延迟访问其赖以执行的资源，也许是调度器先于低优先级的线程而执行高优先级的线程，同时总是会有一个高优先级的
 * 线程可以执行，饿死也叫做无限延迟
 * @author ： lihewei
 *
 * 工具 jvisualvm
 *
Found one Java-level deadlock:
=============================
"runable2":
waiting to lock monitor 0x00007fa9d8033358 (object 0x00000007956a5570, a java.lang.Object),
which is held by "runable1"
"runable1":
waiting to lock monitor 0x00007fa9d8031eb8 (object 0x00000007956a5580, a java.lang.Object),
which is held by "runable2"

 */
public class MyTest6 {
    private Object lock1=new Object();
    private Object lock2=new Object();

     public  void  method1(){
        synchronized (lock1){
            synchronized (lock2){
                System.out.println("myMehtod invoked");
            }
        }
    }

    public  void method2(){
         synchronized (lock2){
             synchronized (lock1){
                 System.out.println("myMethod2 invoked");
             }
         }
    }

    public static void main(String[] args) {
        MyTest6 myTest6=new MyTest6();

        Runnable runnable=()->{
          while (true){
              myTest6.method1();
              try {
                  Thread.sleep(10);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
          }
        };
        Thread thread=new Thread(runnable,"runable1");

        Runnable runnable2=()->{
          while (true){
              myTest6.method2();
              try {
                  Thread.sleep(25);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };
        Thread thread1=new Thread(runnable2,"runable2");
        thread.start();
        thread1.start();
    }
}
