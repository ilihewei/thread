package com.lihewei.concurrency6;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:lihewei
 * 对于Cas来说，其操作主要涉及到如下三个：
 * 1.需要被操作的内存值v
 * 2.需要进行比较的值A
 * 3.需要进行写入的值B
 * 只有当v==A的时候，CAS才会通过原子操作的手段来将v的值更新为B
 *
 * 关于CAS的限制或是问题
 *
 * 1.循环开销问题，并发量大的情况下回导致线程一直自旋
 * 2.只能保证一个变量的原子操作：可以通过atomicReference来实现对多个变量的原子操作
 * 3，aba问题: 1->3->1
 * 4.timestamp.lock
 *
 *     public final int getAndSetInt(Object var1, long var2, int var4) {
 *         int var5;
 *         do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var4));
 *
 *         return var5;
 *     }
 */
public class MyTest2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);

        System.out.println(atomicInteger.get());//5
        System.out.println(atomicInteger.getAndSet(8));//8
        System.out.println(atomicInteger.get());//8
        System.out.println(atomicInteger.getAndIncrement());//9
        System.out.println(atomicInteger.get());//9
    }
}
