package com.shoufeng.singleton;

/**
 * 双检索
 */
public class SingletonDoubleLock {
  private volatile static SingletonDoubleLock singletonDoubleLock;
  private SingletonDoubleLock() {
  }
  public SingletonDoubleLock getInstance(){
    if (singletonDoubleLock == null){
      synchronized (SingletonDoubleLock.class){
        if (singletonDoubleLock == null){
          singletonDoubleLock = new SingletonDoubleLock();
          return singletonDoubleLock;
        }
      }
    }
    return singletonDoubleLock;
  }
}
