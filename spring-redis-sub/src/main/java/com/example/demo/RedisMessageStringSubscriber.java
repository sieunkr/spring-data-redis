package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RedisMessageStringSubscriber implements MessageListener {

    public void onMessage(Message message, byte[] pattern) {

        log.info("String Message received: " + message.toString());
    }
}