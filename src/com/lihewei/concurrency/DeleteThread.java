package com.lihewei.concurrency;

public class DeleteThread  extends  Thread{
    private MyObject myObject;

    public DeleteThread(MyObject myObject){
        this.myObject=myObject;
    }

    @Override
    public  void run(){
      //  try {
           // sleep(1000);
       // } catch (InterruptedException e) {
         //   e.printStackTrace();
        //}
        for (int i = 0; i <30 ; i++) {
            myObject.deleteOne();
        }
    }
}
