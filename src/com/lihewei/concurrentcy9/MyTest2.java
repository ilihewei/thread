package com.lihewei.concurrentcy9;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MyTest2 {
    public static void main(String[] args) {
        ExecutorService executorService=new ThreadPoolExecutor(3,5,0,
                TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(3),new ThreadPoolExecutor.CallerRunsPolicy());

        IntStream.range(0,9).forEach(i->executorService.submit(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            IntStream.range(0,1).forEach(j->{
                System.out.println(Thread.currentThread().getName());
            });
        })
        );
        executorService.shutdown();
    }
}
