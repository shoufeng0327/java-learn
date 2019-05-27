package com.shoufeng.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyInvocationHandler implements InvocationHandler {

  private Hello hello;

  public ProxyInvocationHandler(Hello hello) {
    this.hello = hello;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if ("sayHello".equalsIgnoreCase(method.getName())){
      System.out.println("开始调用sayHello方法 " + Arrays.toString(args));
      return method.invoke(hello,args);
    }
    return null;
  }

  public static void main(String[] args) {
    HelloImpl helloImpl = new HelloImpl();
    Hello hello = (Hello)Proxy.newProxyInstance(helloImpl.getClass().getClassLoader(),
        helloImpl.getClass().getInterfaces(), new ProxyInvocationHandler(helloImpl));
    hello.sayHello("zhangsan");
  }
}
