package com.shoufeng.reentrant;

/**
 * 1.写两个线程，一个线程打印1~52，另一个线程打印A~Z，打印顺序是12A34B...5152Z；
 * 思路分析，一个线程打印数字，每次打印两个数字，26次打印完，另一个线程打印字母，也是26次打印完；
 * 可以创建两个方法，一个方法打印数字，一个打印字母；还有创建一个全局变量用来控制具体执行的是哪个线程；每个方法都被执行26次。
 */
public class NomalPrint {
    public static void main(String[] args) {
        MyPrint myPrint = new MyPrint();
        new Thread(() -> {
            try {
                myPrint.printNum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                myPrint.printEnglish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class MyPrint{
        synchronized void printNum() throws InterruptedException {
            int num = 1;
            while (num <= 26){
                System.out.print(num*2 - 1);
                System.out.print(num*2);
                num++;
                this.notify();
                if (num <= 26){
                    this.wait();
                }
            }
        }

        synchronized void printEnglish() throws InterruptedException {
            int num = 1;
            char en = 'A';
            while (num <= 26){
                System.out.print(en);
                en++;
                num++;
                this.notify();
                if (num <= 26){
                    this.wait();
                }
            }
        }
    }
}
