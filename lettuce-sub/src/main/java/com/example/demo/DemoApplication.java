package com.example.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.pubsub.api.reactive.RedisPubSubReactiveCommands;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {


        /*
        // 1. Synchronous subscription

        RedisClient client = RedisClient.create("redis://localhost");

        StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();
        connection.addListener(new RedisPubSubListener<String, String>() {
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
        });

        RedisPubSubCommands<String, String> sync = connection.sync();
        sync.subscribe("ch01");
        */

        /*
        // 2. Asynchronous subscription
        RedisClient client = RedisClient.create("redis://localhost");

        StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();
        connection.addListener(new RedisListener());

        RedisPubSubAsyncCommands<String, String> async = connection.async();
        async.subscribe("ch01");
        */


        // 3. Asynchronous subscription
        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();

        RedisPubSubReactiveCommands<String, String> reactive = connection.reactive();
        reactive.subscribe("ch01").subscribe();

        reactive.observeChannels().doOnNext(patternMessage -> {
            System.out.println("channel: " + patternMessage.getChannel() + ", " +
                    "message: " + patternMessage.getMessage());
        }).subscribe();
        //... 비동기 & 논블록킹

    }
}