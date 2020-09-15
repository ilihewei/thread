package com.lihewei.concurrency6;

/**
 *
 * CAS
 * 1.synchronzed 关键字与lock等锁机制都是悲观锁：无论做如何操作，首先都需要先上锁，接下来再去执行后续操作，
 * 从而确保了接下来所有操作都是由当前这个线程来执行的
 * 2.乐观锁：线程在操作之前不会做任何预先处理，而是直接去执行，当在最后执行变量的时候，当线程需要有一种机制来确保
 * 当前被操作的变量是没有被其他线程修改的，cas是乐观锁的一种极为重要的实现方式
 *
 * cas（compare and swap）
 * 比较与转换：这是一个不断循环的过程，一直到变量被修改成功为止，cas本身是由硬件指令提供支持的，硬件中是通过一个原子指令
 * 来实现比较与交换的，因此cas可以确保变量操作的原子性的
 */
public class MyTest1 {
    private int count;

    public synchronized int getCount() {
        return count;
    }



    /**
     * 读取-->修改-->写入（这三个操作并非原子操作）
     */
    private synchronized void increaseCount(){
        ++this.count;
    }
}
