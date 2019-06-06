package com.shoufeng.redis;

import redis.clients.jedis.JedisPubSub;

public class JedisPubSubDemo extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);
    }
}
