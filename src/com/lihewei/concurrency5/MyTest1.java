package com.lihewei.concurrency5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author  lihewei
 * CountDownLatch
 */
public class MyTest1 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch=new CountDownLatch(3);
        IntStream.range(0,3).forEach(i->new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.println("hello world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        }).start());
        System.out.println("启动自线程完毕");

        try {
            //减少到0才会调用这个方法
            countDownLatch.await(20, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行完毕");
    }

}
