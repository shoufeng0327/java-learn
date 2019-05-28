package com.shoufeng.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    //synchronizedMap和hashtable都只适合在非高并发
    map = Collections.synchronizedMap(map);

    //1.7Segment分段锁，默认16，1.8cas
    ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
  }
}
