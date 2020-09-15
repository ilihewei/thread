package com.lihewei.test;

/**
 * @author :lihewei
 */
public class TestDemo {
    public static void main(String[] args) {
        final int a=2;
        final byte a2=3;
        System.out.println(a);
        int a3=a+a2;
        //final int和final byte相加是整形和byte型都可以，不需要强制转换。
        byte a4=a+a2;
        System.out.println(a4);
        System.out.println(2>>>2);

    }
}
