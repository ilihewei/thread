package com.lihewei.concurrency1;

public class IncreaseThread  extends Thread{

    private  MyObject myObject;

    public IncreaseThread(MyObject myObject){
        this.myObject=myObject;
    }


    @Override
    public  void run(){
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i <30 ; i++) {
            myObject.addOne();
        }
    }
}
