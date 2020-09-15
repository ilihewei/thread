package com.lihewei.concurrency2;

public class MyThreadTest {
    public static void main(String[] args) {
        Runnable runnable = new MyThread();
        Thread thread=new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        thread.start();
        thread1.start();
    }
}

class MyThread implements Runnable {

    private   int x;

    @Override
    public void run() {
        x = 0;
        while (true) {
            synchronized (this) {
                try {
                    System.out.println("x===>" + x++);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (x == 30) {
                    break;
                }
            }
        }
    }
}


