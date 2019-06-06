package com.shoufeng.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实例化三个线程，一个线程打印a，一个线程打印b，一个线程打印c，三个线程同时执行，要求打印出10个连着的abc。
 */
public class ABCReentrantLockPrint1 {
    public static void main1(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionA = reentrantLock.newCondition();
        Condition conditionB = reentrantLock.newCondition();
        Condition conditionC = reentrantLock.newCondition();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("conditionB阻塞");
                conditionB.await();
                System.out.println("conditionB被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("B unlock");
                conditionC.signal();
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("conditionC阻塞");
                conditionC.await();
                System.out.println("conditionC被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("C unlock");
                conditionA.signal();
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                conditionB.signal();
                System.out.println("conditionA阻塞");
                conditionA.await();
                System.out.println("conditionA被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("A unlock");
                reentrantLock.unlock();
            }
        }).start();
    }

    /**
     * 当多个线程竞争的时候，lock了几次就要unlock几次，单个线程的话无所谓
     * @param args
     */
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(() -> {
            reentrantLock.lock();
            reentrantLock.lock();
            try {
                System.out.println("aaaaaa");
            }finally {
                reentrantLock.unlock();
            }
        }).start();

//        new Thread(() -> {
//            reentrantLock.lock();
//            try {
//                System.out.println("bbbbbb");
//            }finally {
//                reentrantLock.unlock();
//            }
//        }).start();
    }
}
