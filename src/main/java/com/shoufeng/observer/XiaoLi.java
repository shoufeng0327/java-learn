package com.shoufeng.observer;

public class XiaoLi implements Person{

  @Override
  public void getMessage(String s) {
    System.out.println("小李收到来自小美的消息: " + s);
  }
}
