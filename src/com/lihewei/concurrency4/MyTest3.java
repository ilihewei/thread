package com.lihewei.concurrency4;

/**
 * volatile 关键字
 * private volatile int count；
 * @author ：lihewei
 *
 * volatile 关键字主要有三方面作用：
 * 1.实现long/double 类型变量的原子操作
 * 2.防止指令重排序
 * 3.实现变量的可见性
 * validate double a=1.8；
 * 当用validate修饰变量，应该就不会从寄存器中读取值，从高速缓存（内存中）读取
 * validate 与锁类似的作用？
 * 1.确保变量的可见性
 * 2.防止指令重排序
 * validate 可以确保对变量写的原子性，不具备排他性
 *
 * 另外重要一点在于，使用锁可能会导致线程上下文的切换（内核态与用户态的转化），使用validate不会有这种情况
 * validate int a=b+2;
 * validate int a=a++;
 * validate int count =1;
 * validate boolean flag=false;
 * 如果实现validate 写操作的原子性，那么在符号右侧的赋值变量中
 * 就不能出现多线程的变量，哪怕这个变量也是validate也不可以
 * validate Date date=new Date();
 *
 *
 * 防止指令重排序与实现变量的可见性都是通过一种手段来实现的，内存屏障
 * （memory barrier）
 *
 * 内存屏障（Release barrier,释放屏障）
 * volidate boolean =false;//写入操作
 * 内存屏障（Store barrier，存储屏障）
 *
 * release barrier防止下面的volidate 与上面的所有的操作的指令重排序
 * store barrier ：重要作用是刷新处理缓存，结果是可以确保该存储屏障之前
 * 一切的操作生成的结果对于其他处理器来说都可以
 *
 * 内存屏障（load barrier，加载屏障）
 * boolean v1=v;
 *
 * 内存屏障（acquire barrier ，获取屏障）
 * int a=1；
 * String s="hello"；
 * load barrier：可以刷新处理器缓存，同步其他的处理器
 * 对该volidate变量的修改结果，acquire barrier
 * 可以防止上面的volidate 读取操作与下面的所有操作者语句重排序
 *
 * 对于volidate 关键字变量的读写操作，本质都是通过屏障来执行的
 * 内存屏障兼具了两方面能力：
 * 1，防止指令重排序，2实现变量内存的可见性
 *
 * 1.对于读取操作来说，volidate 可以确保该操作与其后续所有的操作都不会进行指令重排序
 * 2.对于修改操作来说，volidate可以确保该操作与其上面的所有的读写操作都不会进行指令排序
 *
 *
 * volidate 与锁的一些比较
 * 锁同样具有变量内存可见性与防止指令重排序的功能
 *
 *monitorenter
 * 内存屏障（acuqire barrier ，获取屏障）
 *
 *
 * 内存屏障（releasse barrier ，释放屏障）
 * monitorexit
 */
public class MyTest3 {


}
