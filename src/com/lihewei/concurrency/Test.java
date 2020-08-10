package com.lihewei.concurrency;

public class Test {
    public static void main(String[] args) {
        MyObject myObject=new MyObject();
        Thread increaseThread = new IncreaseThread(myObject);
        DeleteThread deleteThread = new DeleteThread(myObject);
        Thread increaseThread2 = new IncreaseThread(myObject);
        DeleteThread deleteThread2 = new DeleteThread(myObject);

        increaseThread2.start();
        deleteThread2.start();
        increaseThread.start();
        deleteThread.start();
    }
}
