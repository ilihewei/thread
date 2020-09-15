package com.lihewei.currentDemo;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MyTest5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService=new ThreadPoolExecutor(4,10,10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(20),new ThreadPoolExecutor.AbortPolicy());

        CompletionService<Integer> completionService=new ExecutorCompletionService<>(executorService);

        IntStream.range(0,10).forEach(i->{
            completionService.submit(()->{
               Thread.sleep((long) Math.random()*1000);
                System.out.println(Thread.currentThread().getName());
                return i;
            });
        });

        for (int i=0;i<10;i++) {
            Integer integer = completionService.take().get();
            System.out.println(integer);
        }
        executorService.shutdown();
    }
}





