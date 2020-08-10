package com.lihewei.concurrency2;

/**
 * @author lihewei
 */
public class MyThreadTest2 {
    public static void main(String[] args) {

        MyClass myClass=new MyClass();
        MyClass myClass1=new MyClass();

        Thread1 thread1=new Thread1(myClass);
        /**
         * 如果传入的是同一个对象，在同一个对象上有多个syn方法，同一时间只能有一个方法生效，获得锁的监视器
         */


        //Thread2 thread2=new Thread2(myClass);

        /**
         * 如果同一个类new 出不同的实例，本质是访问不同的实例的锁，不收影响
         */
        Thread2 thread2=new Thread2(myClass1);

        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
class MyClass{
    public  synchronized void hello(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public  synchronized  void wolrd(){
        System.out.println("world");
    }
}
class  Thread1 extends Thread{
    private  MyClass myClass;
    public  Thread1(MyClass myClass){
        this.myClass=myClass;
    }

    @Override
    public  void run(){
        myClass.hello();
    }

}
class  Thread2 extends Thread{
    private  MyClass myClass;
    public  Thread2(MyClass myClass){
        this.myClass=myClass;
    }

    @Override
    public  void run(){
        myClass.wolrd();
    }

}