package com.lihewei.concurrency7;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MyTest2 {
    public static void main(String[] args) {
        /**
        String join = CompletableFuture.supplyAsync(() -> "hello").thenApplyAsync(value->value+"world").join();
        System.out.println(join);
        System.out.println("===================");
        CompletableFuture.supplyAsync(()->"hello").thenAccept(value->System.out.println("welcome "+value));

        System.out.println("==================");

        //对stage都是异步的
        String result2=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }),(s1,s2)->s1+" "+s2).join();

        System.out.println(result2);

        **/

        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
            try {
                TimeUnit.MICROSECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finished");
        });


        completableFuture.whenComplete((t,action)-> System.out.println("执行完成"));

        System.out.println("主线程执行完毕");

        try {
            TimeUnit.MICROSECONDS.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
