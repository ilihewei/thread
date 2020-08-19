package com.lihewei.concurrency4;

/**
 * java  内存机制（Java memeory model）以及happen-before
 * 1.变量的原子行问题
 * 2.变量的可见性问题
 * 3.变量修改的时序行问题
 *
 *
 * happen-before重要规则
 *
 * 1.顺序执行规则（限定在哪个线程上的），该线程的每个动作都happen-before他的动作
 * 2.隐士锁（monitor）规则：unlock hapen-before lock，之前的线程对于同步代码块的所有的所有执行结果对于
 * 获取锁的线程来说都是可见的
 * 3.voldiate 读写规则：对于一个volidate变量的操作一定会happen-before后续变量的°操作
 * 4.多线程的启动规则：thread对象的start方法happen-before该线程run
 * 方法中的一个动作，包括在其中启动的任何子线程
 * 5.多线程的终止规则：一个线程启动了一个线程，并且调用了子线程的join方法等待其结束，父线程的接下来
 * 的所有操作都可以能考到子线程run方法的中的执行结果
 * 6.线程中断规则，可以使用interrupt方法来中断线程，这个调用happen-before对该线程中断的检查
 * （interrupted）
 *  https://www.jianshu.com/p/1508eedba54d
 */
public class MyTest4 {
}
