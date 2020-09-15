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
 *
 * 关于线程池任务提交的总结：
 * 1.两种提交的方式： sumbit 和execute
 * 2.submit 有两种方式，无论哪种方式，最终都是将传递进来的任务转换为一个Callcable
 * 对象进行处理
 * 3.当Callable构造完毕后，最后都会调用executor接口中声明的execute方法进行统一的处理
 *
 * 对线程池来说，存在两个状态需要维护
 * 1.线程池本身的状态：ctl的高3位来表示
 * 2.线程池所运行的线程的数量：通过ctl的其余29位来表示
 *
 *  线程池的状态：
 *  1.RUNNING：线程池可以接收新的任务提交，并且还可以正常处理阻塞队列中的任务。
 *  2.SHUTDOWN： 不再接收新的任务提交，不过线程池可以继续处理阻塞队列中的任务
 *  3.STOP：不再接收新的任务，同时还会丢弃既有任务；此外，他还会中断正在处理中的出任务。
 *  4.TIDYING：所有的任务都执行完毕，当前线程池中的活动的线程数量降为0，将会调用treminated方法
 *  5.terminated： 线程的终止状态，当terminated方法执行完毕后，线程池将会处在该状态之下
 *
 *
 *      RUNNING-->SHUTDOWN :当调用了线程池的shutdown方法时，或者当finalize方法被隐士调用后，（该方法内部会调用shutdown方法）
 *      RUNNING，shutdown-->stop  当调用了线程池的shutdown方法
 *      SHUTDOWN-->TIDYING: 在线程池与阻塞队列变为空时
 *      STOP-->TIDYING:在线程池变为空时
 *      TIDYING--> TERMINATED:  在terminated方法被执行完毕时。
 *
 *
 *
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
