package com.shoufeng.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

public class JedisDemo {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        HashMap<String, String> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        jedis.hmset("maptest",map);
//        jedis.lpush("lpushtest","6");
//        jedis.lpush("lpushtest","1");
//        jedis.lpush("lpushtest","2");
//        jedis.lpush("lpushtest","3");
//        jedis.lpush("lpushtest","4");
//        jedis.lpush("lpushtest","5");
//        String lpushtest = jedis.lpop("lpushtest");
//        String rpop = jedis.rpop("lpushtest");
//        System.out.println(rpop);
//        List<String> list = jedis.sort("lpushtest");
//        list.forEach(System.out::println);
        jedis.hset("hsettest","a","q");
        jedis.hset("hsettest","b","q");
        jedis.hset("hsettest","c","q");
    }
}
