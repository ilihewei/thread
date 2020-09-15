package com.lihewei.test;

import java.util.concurrent.atomic.AtomicInteger;

public class MyTest2 {
    static AtomicInteger atomicInteger=new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(atomicInteger.get());    }
}
