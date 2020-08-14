package com.lihewei.concurrency5;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * CyclicBarrier
 */
public class MyTest2 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                new Thread(() -> {
                    try {
                        Thread.sleep((long) (Math.random() * 6000));
                        int nextInt = new Random().nextInt(500);
                        System.out.println("hello====" + nextInt);
                        try {
                            cyclicBarrier.await();
                            System.out.println("world=====" + nextInt);
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
