package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

    public static List<TestDTO> messageList = new ArrayList<>();

    public void onMessage(Message message, byte[] pattern) {

        //messageList.add(message.toString());
        System.out.println("Message received: " + message.toString());
    }
}