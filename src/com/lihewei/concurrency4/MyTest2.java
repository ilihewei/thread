package com.lihewei.concurrency4;

/**
 * 传统上，我们可以通过synchronized关键字+Wait+notify/notifyAll
 * 来实现多个线程之间的协调与通信，整个过程都是由jvm来帮助我们实现，开发者无需（也是无法）了解底层的实现细节
 *
 * 从jdk 5开始 并发包提供了lock，condition（wait 与signal、signalAll）来实现多个线程之间的协调与通信，整个过程都是由开发者
 * 来控制的，而且相比较传统方式，功能也更加强大
 * @author:lihewei
 * Thread.sleep与awit(或是object的wait方法)的本质区别，sleep方法本质上不会释放锁，而await会释放锁，并且在signal后，还需要
 * 重新获得锁才能继续执行，该行为与object方法完全一致
 */
public class MyTest2 {



}
