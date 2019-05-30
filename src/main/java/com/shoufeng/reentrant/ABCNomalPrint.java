package com.shoufeng.reentrant;


/**
 * 实例化三个线程，一个线程打印a，一个线程打印b，一个线程打印c，三个线程同时执行，要求打印出10个连着的abc。
 */
public class ABCNomalPrint {

    public static void main(String[] args) {
        MyPrint myPrint = new MyPrint();
        new Thread(() -> {
            try {
                myPrint.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                myPrint.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                myPrint.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class MyPrint{

    private volatile int flag = 1;

    synchronized void printA() throws InterruptedException {
        for (int i = 0; i < 10; ) {
            if (flag == 1){
                System.out.println(i + "A");
                flag = 2;
                i++;
                this.notifyAll();
                while (flag != 1 && i != 10){
                    this.wait();
                }
            }
        }
    }

    synchronized void printB() throws InterruptedException {
        for (int i = 0; i < 10;) {
            if (flag == 2){
                System.out.println(i + "B");
                flag = 3;
                i++;
                this.notifyAll();
                while (flag != 2 && i != 10){
                    this.wait();
                }
            }
        }
    }

    synchronized void printC() throws InterruptedException {
        for (int i = 0; i < 10;) {
            if (flag == 3){
                System.out.println(i + "C");
                flag = 1;
                i++;
                this.notifyAll();
                while (flag != 3 && i != 10){
                    this.wait();
                }
            }
        }
    }
}
