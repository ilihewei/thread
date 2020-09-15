package com.lihewei.concurrency4;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

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

    public static void main(String[] args) {
        BoundedContainer boundedContainer = new BoundedContainer();
        IntStream.range(0,5).forEach(i->new Thread(()->{
           boundedContainer.getTake();
        }).start());


        IntStream.range(0,10).forEach(i->new Thread(()->{
            boundedContainer.getPut("hello");
        }).start());

    }
}

class BoundedContainer{

    private  String[] elements=new String[10];

    private  Lock lock=new ReentrantLock();

    private Condition notEmptyCondition=lock.newCondition();

    private Condition notFullCondition=lock.newCondition();

    //elements的值得数量
    private  int elementCount;


    private  int putIndex;

    private int takeIndex;

    public  void getPut(String element){
        this.lock.lock();
        try {
            while (this.elementCount==this.elements.length){
                notFullCondition.await();
            }
            elements[putIndex]=element;

            if (++putIndex==this.elements.length){
                putIndex=0;
            }

            ++elementCount;
            System.out.println("put method===>"+ Arrays.toString(elements));
            notEmptyCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }

    }

    public  void getTake(){
        this.lock.lock();
        try {
            while (this.elementCount==0){
                notEmptyCondition.await();
            }

            String element=elements[takeIndex];
            elements[takeIndex]=null;
            if (takeIndex++==this.elements.length){
                takeIndex=0;
            }
            --elementCount;
            System.out.println("take method==>"+Arrays.toString(elements));
            notFullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

}
