package com.shoufeng.volatil;

public class VolatileDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in ");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.addTo60();
            System.out.println(Thread.currentThread().getName() + " update flag 200 ");
        }).start();

        while (true){
            if (demo.num != 0){
                System.out.println("=====");
            }
        }
    }
}
class Demo{
//    public int num = 0;
    public volatile int num = 0;

    public void addTo60(){
        num = 60;
    }
}
