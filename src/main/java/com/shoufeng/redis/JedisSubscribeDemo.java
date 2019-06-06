package com.shoufeng.redis;

import redis.clients.jedis.Jedis;

public class JedisSubscribeDemo {
    public static void main(String[] args) {
        //连接redis服务器，192.168.0.100:6379
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //权限认证
        //jedis.auth("admin");
        jedis.subscribe(new JedisPubSubDemo(),"jedispublishtest");
//        jedis.publish("jedispublishtest","11111111");
    }
}
