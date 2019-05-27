package com.shoufeng.cglib;

import java.lang.reflect.Method;
import java.util.Arrays;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {

  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
    if ("sayHi".equalsIgnoreCase(method.getName())){
      System.out.println("开始sayHi " + Arrays.toString(objects));
      return methodProxy.invokeSuper(o,objects);
    }
    return null;
  }

  public static void main(String[] args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(Hi.class);
    enhancer.setCallback(new MyMethodInterceptor());
    Hi hi = (Hi) enhancer.create();
    hi.sayHi("lisi");
  }
}
