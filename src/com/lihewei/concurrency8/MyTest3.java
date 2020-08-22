package com.lihewei.concurrency8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyTest3 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try {

            Condition condition = lock.newCondition();
        }finally {
            lock.unlock();
        }
    }
}
