package com.lihewei.currentDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDK的并发包提供了一些类类支持原子操作，如Atomicboolean（用原子方式更新的boolean的值），
 * atomicinteger（用原子方式更新int值）和AtomicLong（用原子方式更新的long值）。这些原子包装类还提供了有用的工具
 * 方法，比如以原子的方式将当前值加1，减1
 *
 * CAS实现原子操作的三大问题
 * ABA 问题
 *
 *
 * 循环时间长开销大
 * 只能保证一个共享变量的原子操作
 */
public class Counter {
    private AtomicInteger atomicInteger=new AtomicInteger(0);

    private  int i=0;

    public static void main(String[] args) {
        final  Counter cas =new Counter();
        List<Thread>  ts=new ArrayList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                            cas.count();
                          //  cas.safeCount();
                    }
                }
            });
            ts.add(t);
            }

            for (Thread t:ts){
                t.start();
            }

            for (Thread t:ts){
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis()-start);
    }

    private void safeCount() {

        for (;;){
            int i = atomicInteger.get();
            boolean b = atomicInteger.compareAndSet(i, ++i);
            if (b){
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
