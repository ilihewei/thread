package com.lihewei.currentDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MyTest4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool=new ForkJoinPool();
        MyTask myTask1 = new MyTask(0, 40);
        Integer invoke = forkJoinPool.invoke(myTask1);
        System.out.println(invoke);
        forkJoinPool.shutdown();
    }
}

class  MyTask  extends RecursiveTask<Integer>{

    private  int limit=4;
    private  int firstIndex;
    private  int endIndex;

    public  MyTask(int firstIndex,int endIndex){
        this.firstIndex=firstIndex;
        this.endIndex=endIndex;
    }


    @Override
    protected Integer compute() {

        int result=0;
        int gap=endIndex-firstIndex;
        boolean flag=gap<=limit;

         if (flag){
             System.out.println(Thread.currentThread().getName());
             for (int i=firstIndex;i<=endIndex;i++){
                 result +=i;
             }

         }else {

             int middleIndex=(firstIndex+endIndex)/2;

             MyTask leftTask=new MyTask(firstIndex,middleIndex);
             MyTask rightTask=new MyTask(middleIndex+1,endIndex);
             invokeAll(leftTask,rightTask);


             int leftTaskResult=leftTask.join();
             int rightTaskResult=rightTask.join();

             result= leftTaskResult+rightTaskResult;
         }

        return result;
    }
}
