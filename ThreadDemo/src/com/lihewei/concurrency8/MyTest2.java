package com.lihewei.concurrency8;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 *关于ReetranReadWriteLock的操作逻辑；
 * 读锁：
 *  1. 在获取读锁时，会尝试判断当前对象是否拥有了写锁，如果已经拥有，则直接失败
 *  2.如果没有写锁，就表示当前对象没有排他锁，则当前线程会尝试给对象加锁
 *  3.如果当前线程已经拥有持有了该对象的锁，那么直接将读锁数量加1
 *
 *  写锁：
 *  1. 在获取写锁时，会尝试判断当前对象是否拥有了锁（读锁与写锁），如果已经拥有且持有的线程并非当前线程，直接失败
 *  2.如果当期对象没有被加锁，那么写锁就会为当前对象上锁，并且将写锁的个数+1
 *  3.将当前对象的排他锁线程持有者设为自己
 *
 *
 *
 *  关于aqs与synchronzed的关键字的关系：
 *   1，syncharonized 关键字在底层的c++实现中，存在两个重要的数据结构（集合）：waitset ，entrylist
 *   2waitset中存放的是调用了Object的wait方法的线程对象（被封装成c++de1Node 对象
 *   3. entrylist 中存放的是陷入到阻塞状态，需要获取monitor的那些对象
 *   4.当一个线程被notify后，他就会从waitset中移动到entrylist中
 *   5，进入到entrylist中，该线程依然需要与其他线程争抢monitor对象
 *   6.如果抢到，就表示该线程获取到了对象的锁，他就可以以排他方式执行对应的同步代码
 *
 *
 *
 *   1.AQS中存在两种队列，分别是condition对象上的条件队列，以及AQS本身的阻塞队列
 *   2.这两个队列中的每个对象都是node实例，（里面封装了线程对象）
 *   3.当位于Condition条件队列中的线程被其他线程signal后，该线程就会从条件中移动到AQS的阻塞队列中
 *   4，位于AQS阻塞队列中的nOde对象本质都是由一个双向链表来构成的
 *   5.在获取AQS锁时，这些进入到阻塞队列中的线程会按照在队列中的排序先后尝试获取
 *   6.当 aqs 阻塞队列中的线程获取到锁后，就表示该线程已经可以进入正常执行了
 *   7.陷入到阻塞状态的线程，依然需要进入到操作系统的内核态，进入阻塞（park方法实现 ）
 */
public class MyTest2 {

    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    public  void  method(){

       // readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock();
        try {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method");
        }finally {
            readWriteLock.writeLock().unlock();
          //  readWriteLock.readLock().unlock();
        }

    }
    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();
        IntStream.range(0,10).forEach(i->new Thread(myTest2::method).start());
    }
}
