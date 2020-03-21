package com.example.demo;

import io.lettuce.core.pubsub.RedisPubSubListener;

public class RedisListener implements RedisPubSubListener<String, String> {


    @Override
    public void message(String channel, String message) {
        System.out.println("channel: " + channel + ", message: " + message);
    }

    @Override
    public void message(String pattern, String channel, String message) {

    }

    @Override
    public void subscribed(String channel, long count) {

    }

    @Override
    public void psubscribed(String pattern, long count) {

    }

    @Override
    public void unsubscribed(String channel, long count) {

    }

    @Override
    public void punsubscribed(String pattern, long count) {

    }
}
