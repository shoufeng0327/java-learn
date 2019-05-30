package com.shoufeng.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实例化三个线程，一个线程打印a，一个线程打印b，一个线程打印c，三个线程同时执行，要求打印出10个连着的abc。
 */
public class ABCReentrantLockPrint {
    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionA = reentrantLock.newCondition();
        Condition conditionB = reentrantLock.newCondition();
        Condition conditionC = reentrantLock.newCondition();

        new Thread(() -> {
            int i = 1;
            reentrantLock.lock();
            try {
                while (i <= 10){
                    System.out.println(i + "A");
                    i++;
                    conditionB.signal();
                    if (i <= 10){
                        System.out.println("conditionA.await();");
                        conditionA.await();
                        System.out.println("conditionA.signal();");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("reentrantLock.unlock();");
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            int i = 1;
            reentrantLock.lock();
            try {
                while (i <= 10){
                    System.out.println(i + "B");
                    i++;
                    conditionC.signal();
                    if (i <= 10){
                        System.out.println("conditionB.await();");
                        conditionB.await();
                        System.out.println("conditionB.signal();");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("reentrantLock.unlock();");
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            int i = 1;
            reentrantLock.lock();
            try {
                while (i <= 10){
                    System.out.println(i + "C");
                    i++;
                    conditionA.signal();
                    if (i <= 10){
                        System.out.println("conditionC.await();");
                        conditionC.await();
                        System.out.println("conditionC.signal();");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("reentrantLock.unlock();");
                reentrantLock.unlock();
            }
        }).start();
    }
}
