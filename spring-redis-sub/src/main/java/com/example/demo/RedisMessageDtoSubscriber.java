package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RedisMessageDtoSubscriber implements MessageListener {

    private static List<CoffeeDTO> coffeeDTOS = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public void onMessage(Message message, byte[] pattern) {

        try {

            CoffeeDTO coffee = mapper.readValue(message.getBody(), CoffeeDTO.class);
            coffeeDTOS.add(coffee);

            log.info("DTO Message received: " + message.toString());
            log.info("Total CoffeeDTO's size: " + coffeeDTOS.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}