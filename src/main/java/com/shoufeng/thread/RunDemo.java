package com.shoufeng.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RunDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo1 demo1 = new Demo1();
        Demo2 demo2 = new Demo2();
        Demo3 demo3 = new Demo3();
        FutureTask<String> futureTask = new FutureTask<>(demo3);
        new Thread(demo1).start();
        new Thread(demo2).start();
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}

class Demo1 extends Thread{
    @Override
    public void run() {
        System.out.println("11111111");
    }
}

class Demo2 implements Runnable{

    @Override
    public void run() {
        System.out.println("22222222");
    }
}

class Demo3 implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "33333333333";
    }
}
