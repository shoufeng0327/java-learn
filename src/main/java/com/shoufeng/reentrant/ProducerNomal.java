package com.shoufeng.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerNomal {
    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(() -> {
            try {
                demo.sayHi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            demo.sayHello();
        }).start();
        new Thread(() -> {
            try {
                demo.sayHi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class Demo{

    private ReentrantLock reentrantLock = new ReentrantLock();

    public void sayHi() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " hi ");
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
                Thread.sleep(3000L);
            }
        }
    }

    public void sayHello(){
        for (int i = 0; i < 10; i++) {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " hello ");
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }
    }

}