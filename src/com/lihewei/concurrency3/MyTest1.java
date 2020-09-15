package com.lihewei.concurrency3;

/**
 * @author lihewei
 * 当我们使用synchr关键字来修饰代码块，字节码层面上是通过monitorenter与monterexit指令来实现锁的获取与释放动作。
 *
 */
public class MyTest1 {

    private  Object object=new Object();
    public  void method(){
        synchronized (object){
            System.out.println("hello world");
            //当有具体的显示异常时，数据直接抛出，不会走monterexit
            throw  new RuntimeException("test");
        }
    }

    public  void method2(){
        synchronized (object){
            System.out.println("mothod2");
        }
    }

    public static void main(String[] args) {

        MyTest1 myTest1=new MyTest1();
        myTest1.method();
    }
    /**
     *  public void method();
     *     Code:
     *        0: aload_0
     *        1: getfield      #3                  // Field object:Ljava/lang/Object;
     *        4: dup
     *        5: astore_1
     *        6: monitorenter
     *        7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *       10: ldc           #5                  // String hello world
     *       12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       15: aload_1
     *       16: monitorexit
     *       17: goto          25
             *       20: astore_2
             *       21: aload_1
             *       22: monitorexit       //异常发生，释放锁
             *       23: aload_2
     *       24: athrow
     *       25: return
     *     Exception table:
     *        from    to  target type
     *            7    17    20   any
     *           20    23    20   any
     */
}
