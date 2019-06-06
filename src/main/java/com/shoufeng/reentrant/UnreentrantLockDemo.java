package com.shoufeng.reentrant;

import java.util.concurrent.atomic.AtomicReference;

public class UnreentrantLockDemo {
    public static void main(String[] args) {
        UnreentrantLock unreentrantLock = new UnreentrantLock();
        System.out.println("开始");
        System.out.println("lock1");
        unreentrantLock.lock();
        System.out.println("lock2");
        unreentrantLock.lock();
    }
}

/**
 * 自旋锁.不可重入锁
 */
class UnreentrantLock{

    private AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void lock(){
        while (true){
            if (threadAtomicReference.compareAndSet(null,Thread.currentThread())){
                return;
            }
        }
    }

    public void unLock(){
        threadAtomicReference.compareAndSet(Thread.currentThread(),null);
    }
}
