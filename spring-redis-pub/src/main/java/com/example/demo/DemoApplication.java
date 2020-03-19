package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, TestDTO> redisTestTemplate;

    @Autowired
    private RedisTemplate<String, String> redisCommonStringTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        redisCommonStringTemplate.convertAndSend("ch01", "string");

        redisTestTemplate.convertAndSend("ch01", TestDTO.builder().name("a").price(500).build());
    }
}