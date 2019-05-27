package com.shoufeng.proxy;

public class HelloImpl implements Hello{

  public void sayHello(String name) {
    System.out.println("你好啊! " + name);
  }
}
