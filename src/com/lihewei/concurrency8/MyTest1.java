package com.lihewei.concurrency8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author:lihewei
 *
 */

public class MyTest1 {

    private  Lock lock=new ReentrantLock(true);

    private  void method(){
        lock.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method");
        }finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) {
        MyTest1 myTest1=new MyTest1();
        IntStream.range(0,10).forEach(i->{
            new Thread(()->{
                myTest1.method();
            }).start();
        });
    }


}
