package com.lihewei.concurrency3;

/**
 * 对于synchronized 修饰方法来说，并没有出现 monitorenter与monitorexit指令，
 * 而是出现了一个ACC_synchronized 标志
 * jvm 使用了acc_synchronized 访问标志来区分一个方法是否为同步方法，当方法被调用时，调用指令检查该方法是否拥有acc——synchronized
 * 标志，如果有执行线程将会先持有方法所在对象的monitor对象，然后再去执行方法体，然后再去执行方法体，在该方法执行期间，其他任何线程均无
 * 法再获取到这个monitor对象
 */
public class Mytest2 {
    public  synchronized  void method(){
        System.out.println("hello world");
    }

    /**
     *  public synchronized void method();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC, C   同步标志位
     *     Code:
     *       stack=2, locals=1, args_size=1
     *          0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *          3: ldc           #3                  // String hello world
     *          5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *          8: return
     *       LineNumberTable:
     *         line 5: 0
     *         line 6: 8
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       9     0  this   Lcom/lihewei/concurrency3/Mytest2;
     * }
     */
}
