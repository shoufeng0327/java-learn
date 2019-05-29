package com.shoufeng.singleton;

/**
 * 饿汉
 */
public class SingletonHungry {
  private static final SingletonHungry SINGLETON_HUNGRY = new SingletonHungry();

  private SingletonHungry() {
  }

  public SingletonHungry getInstance(){
    return SINGLETON_HUNGRY;
  }
}
