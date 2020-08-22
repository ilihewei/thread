package com.lihewei.concurrency8;

import javax.swing.table.AbstractTableModel;
import java.util.concurrent.atomic.AtomicInteger;

public class MyTest4 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(3);
        System.out.println(atomicInteger.get());
        atomicInteger.compareAndSet(4,6);
        System.out.println(atomicInteger.get());
    }
}
