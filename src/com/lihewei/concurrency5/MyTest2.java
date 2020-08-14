package com.lihewei.concurrency5;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * CyclicBarrier
 * 个人理解：CountDownLatch:我把他理解成倒计时锁
 *
 * 场景还原：一年级期末考试要开始了，监考老师发下去试卷，然后坐在讲台旁边玩着手机等待着学生答题，有的学生提前交了试卷，并约起打球了，等到最后一个学生交卷了，老师开始整理试卷，贴封条，下班，陪老婆孩子去了。
 *
 * 补充场景：我们在玩LOL英雄联盟时会出现十个人不同加载状态，但是最后一个人由于各种原因始终加载不了100%，于是游戏系统自动等待所有玩家的状态都准备好，才展现游戏画面。
 *
 * 抽象图：
 *
 *  每位乘客(线程)上车后，可用座位减1，直到为0，老司机就开始发车了。
 *
 *
 *
 * 个人理解：CyclicBarrier:可看成是个障碍，所有的线程必须到齐后才能一起通过这个障碍
 *
 * 场景还原：以前公司组织户外拓展活动，帮助团队建设，其中最重要一个项目就是全体员工（包括女同事，BOSS）在完成其他项目时，到达一个高达四米的高墙没有任何抓点，要求所有人，一个不能少的越过高墙，才能继续进行其他项目。
 *
 * 抽象图：
 *
 *  解放军叔叔完美配合，一个都不能少，继续完成任务。
 */
public class MyTest2 {
    public static void main(String[] args) {
        //cycleBarrier的可以被重置
        CyclicBarrier cyclicBarrier=new CyclicBarrier(3,()->{
            System.out.println("hello world");
        });
        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                new Thread(() -> {
                    try {
                        Thread.sleep((long) (Math.random() * 6000));
                        int nextInt = new Random().nextInt(500);
                        System.out.println("hello====" + nextInt);
                        try {
                            cyclicBarrier.await();
                            System.out.println("world=====" + nextInt);
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
