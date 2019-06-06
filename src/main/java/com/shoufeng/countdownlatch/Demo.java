package com.shoufeng.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Demo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            System.out.println(Thread.currentThread().getName() + "开始 countDownLatch await");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束 countDownLatch await");

        }).start();
        new Thread(() -> {
            System.out.println("-1");
            countDownLatch.countDown();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-2");
            countDownLatch.countDown();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-3");
            countDownLatch.countDown();
        }).start();
    }
}
