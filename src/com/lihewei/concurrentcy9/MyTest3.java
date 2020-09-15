package com.lihewei.concurrentcy9;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MyTest3 {
    public static void main(String[] args) {
        ExecutorService executorService=new ThreadPoolExecutor(3,5,0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(3),
                new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0,10).forEach(i->{
            executorService.submit(()->{
                System.out.println("method");
            });
        });
    }
}
