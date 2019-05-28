package com.shoufeng.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkHashMapDemo {
  public static void main(String[] args) {
    Map<String,Integer> dd=new LinkedHashMap<String,Integer>(10,0.75f,true){
      // return true 删除频率低的
      @Override
      protected boolean removeEldestEntry(Map.Entry<String,Integer> eldest) {
        //eldest == 22:2  22是最先放进来的
        return size()>3;
      }
    };
    dd.put("22",2);
    dd.put("55",5);
    dd.put("66",6);
    dd.get("22");//调用22 ，那么55就是频率低的，在put “77”时 ，size()>3 返回true ；因此删除 “55”
    dd.put("77",7);
    System.out.println(dd.keySet());//out : [66, 22, 77]
    dd.get("66");
    System.out.println(dd.keySet());//out : [22, 77, 66]
  }
}
