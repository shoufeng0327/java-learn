package com.shoufeng.DeadLock;

/**
 * 死锁
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(() -> {
            try {
                demo.synchA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                demo.synchB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class Demo{

    Character ch1 = '1';
    Character ch2 = '2';

    public void synchA() throws InterruptedException {
        synchronized (ch1){
            System.out.println(Thread.currentThread().getName() + "获取ch1的锁");
            Thread.sleep(3000L);
            System.out.println(Thread.currentThread().getName() + "尝试获取ch2的锁");
            synchB();
        }
    }

    public void  synchB() throws InterruptedException {
        synchronized (ch2){
            System.out.println(Thread.currentThread().getName() + "获取ch2的锁");
            System.out.println(Thread.currentThread().getName() + "尝试获取ch1的锁");
            synchA();
        }
    }
}

