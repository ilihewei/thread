package com.lihewei.concurrency1;

/**
 *
 */
public class MyObject{

    private static   int num;

    public    synchronized  void addOne(){
        while (num!=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(num);
        notify();
    }

    public  synchronized void  deleteOne(){
        while (num==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(num);
        notify();
    }
}
