package com.lihewei.concurrentcy9;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 对于线程来说，其提供了execute和submit两种方式向线程池提交任务
 * 总体来说，sumbit方法是可以取代execute方法的，因为他既可以接受callable任务，也可以接受Runnable任务
 *
 * 关于线程池的总体执行策略：
 * 1.如果线程池中正在执行的线程数<COREPOOLSIZE,那么线程池就会优先选择创建新的线程而非将提交的任务加到阻塞队列中
 * 2.如果线程池正在执行的线程数>=corepoolsize，那么线程池就会优先选择对提交的任务进行阻塞排队而非创建新的线程。
 * 3.如果提交的任务无法加入到阻塞队列当中，那么线程池就会创建新的线程。从阻塞队列中获取新的线程。
 * 如果创建的线程数超过maximumPOOlSIze，那么拒绝策略就会起作用。
 *
 */

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
