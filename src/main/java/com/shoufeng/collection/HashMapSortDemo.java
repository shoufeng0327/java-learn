package com.shoufeng.collection;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 1. HashMap 排序题，上机题。(本人主要靠这道题入职的第一家公司)
 * 已知一个 HashMap<Integer，User>集合， User 有 name(String)和 age(int)属性。
 * 请写一个方法实现对 HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，
 * 返回类型为 HashMap<Integer，User>， 要求对 HashMap 中的 User 的 age 倒序进行排序。
 * 排序时 key=value 键值对不得拆散。 注意:要做出这道题必须对集合的体系结构非常的熟悉。
 * HashMap 本身就是不可排序的，但是该道题偏偏让给 HashMap 排序，
 * 那我们就得想在 API 中有没有这样的 Map 结构是有序的，LinkedHashMap，
 * 对的，就是他，他是 Map 结构，也是链表结构，有序的，更可喜的是他是 HashMap 的子类
 */
public class HashMapSortDemo {
    public static void main(String[] args) {
        HashMap<Integer, User> users = new HashMap<>();
        users.put(1, new User("张三", 25));
        users.put(3, new User("李四", 22));
        users.put(2, new User("王五", 28));
        ArrayList<Map.Entry<Integer, User>> list = new ArrayList<>(users.entrySet());
        Collections.sort(list,(o1, o2) -> {
            return o1.getValue().age - o2.getValue().age;
        });
        LinkedHashMap<Integer,User> map = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> integerUserEntry : list) {
            map.put(integerUserEntry.getKey(),integerUserEntry.getValue());
        }
        System.out.println(JSON.toJSONString(map));
    }
}

class User {

    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
