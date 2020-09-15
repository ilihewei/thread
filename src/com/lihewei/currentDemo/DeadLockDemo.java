package com.lihewei.currentDemo;


public class DeadLockDemo {

    private   static  String A="A";
    private  static  String B="B";

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        deadLockDemo.deadLock();
        deadLockDemo.deadLock2();
    }

    public  void  deadLock(){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (B){
                    System.out.println("1");
                }
            }
        });

//        Thread t2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (B){
//                    synchronized (A){
//                        System.out.println("2");
//                    }
//                }
//            }
//        });
        t1.start();

     //   t2.start();
    }

    public void deadLock2(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println("2");
                    }
                }
            }
        });
        t.start();

    }



}
