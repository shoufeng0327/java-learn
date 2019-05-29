package com.shoufeng.observer;

public class LaoWang implements Person{

  @Override
  public void getMessage(String s) {
    System.out.println("老王收到来自小美的消息: " + s);
  }
}
