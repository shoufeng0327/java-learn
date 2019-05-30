package com.shoufeng.reentrant;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.写两个线程，一个线程打印1~52，另一个线程打印A~Z，打印顺序是12A34B...5152Z；
 * 思路分析，一个线程打印数字，每次打印两个数字，26次打印完，另一个线程打印字母，也是26次打印完；
 * 可以创建两个方法，一个方法打印数字，一个打印字母；还有创建一个全局变量用来控制具体执行的是哪个线程；每个方法都被执行26次。
 */
public class ReentrantLockPrint {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition1 = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();
        new Thread(() -> {
            reentrantLock.lock();
            try {
                int i = 1;
                while (i <= 26){
                    System.out.print(i*2 - 1);
                    System.out.print(i*2);
                    i++;
                    condition2.signal();
                    if (i <= 26){
                        condition1.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                int i = 1;
                char ch = 'A';
                while (i <= 26){
                    System.out.print(ch);
                    ch++;
                    i++;
                    condition1.signal();
                    if (i <= 26){
                        condition2.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();
    }
}

