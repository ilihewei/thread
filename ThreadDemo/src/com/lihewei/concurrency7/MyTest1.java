package com.lihewei.concurrency7;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author  lihewei
 */
public class MyTest1 {
    public static void main(String[] args) {
        Callable<Integer> callable=()->{
            System.out.println("pre execution");
            Thread.sleep(5000);
            int i = new Random().nextInt(100);
            System.out.println("post execution");
            return i;
        };

        FutureTask<Integer> futureTask=new FutureTask<>(callable);

        new Thread(futureTask).start();
        System.out.println("thread has started");

        try {
            Thread.sleep(2000);
            try {
                System.out.println(futureTask.get(1, TimeUnit.MILLISECONDS));
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
