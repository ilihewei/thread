package com.lihewei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 当调用wait方法时，线程必须要持有被调用对象的锁
 * 当调用wait方法以后，线程就会释放掉该对象的锁
 * 在Thread 类的sleep，线程不会释放掉对象的锁
 * @author lihewei
 *
 *
 *
 * 无论正常还是异常，都会调用monitorexit退出，字节码预留各种情况的出口
 *   public static void main(java.lang.String[]) throws java.lang.InterruptedException;
 *     Code:
 *        0: new           #2                  // class java/lang/Object
 *        3: dup
 *        4: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        7: astore_1
 *        8: aload_1
 *        9: dup
 *       10: astore_2
 *       11: monitorenter                  //获得监视器
 *       12: aload_1
 *       13: invokevirtual #3                  // Method java/lang/Object.wait:()V
 *       16: aload_2
 *       17: monitorexit                      异常退出的
 *       18: goto          26
 *       21: astore_3
 *       22: aload_2
 *       23: monitorexit                //正常退出
 *       24: aload_3
 *       25: athrow
 *       26: return
 *     Exception table:
 *        from    to  target type
 *           12    18    21   any
 *           21    24    21   any
 *
 *         关于wait与notify 和notifyAll方法的总结：
 *         1。当调用wait时，首先需要确保调用wait方法的线程已经持有了对象的锁
 *         2。当调用wait后，该线程就会释放掉这个对象的锁，然后进入到等待状态（wait set）
 *         3。当线程调用了wait 后进入到等待状态时，他就可以等待其他线程调用相同对象的notify或ntifyAll方法使的自己被唤醒
 *         4。一旦这个线程被其他线程唤醒后，该线程就会与其他线程一同开始竞争这个对象的锁（公平竞争）：只有当该线程获取到对象的锁之后，
 *         线程才会继续往下执行
 *         5。调用wait方法代码片段虚啊摇妨碍一个syn快或是syn方法中，这样才可以确保在调用wait方法之前已经获取到对象的锁
 *         6。当调用对象的notify方法时，他会随机唤醒对象等待集合（wait set）中的任意一个线程，当某个线程被唤醒后，会与其他线程一同
 *         竞争对象的锁。
 *         7。当调用对象的notifyALl方法时，他会唤醒对象等待集合（wait set）中的所有的线程，这些线程被唤醒，又会开始竞争对象的锁
 *         8。在某一时刻，只有唯一一个线程可以拥有对象的锁。
 *
 * }
 */
public class MyTest1 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Exception in thread "main" java.lang.IllegalMonitorStateException
         * 	at java.lang.Object.wait(Native Method)
         * 	at java.lang.Object.wait(Object.java:502)
         * 	at com.lihewei.concurrency.MyTest1.main(MyTest1.java:9)
         */
        Object o = new Object();
        //o.wait();

        synchronized (o){
            o.wait(10000);
           //  o.notify();
           // System.out.println(o.getClass().);
        }
    }



}
