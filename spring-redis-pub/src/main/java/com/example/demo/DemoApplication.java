package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final RedisTemplate<String, String> stringValueRedisTemplate;
    private final RedisTemplate<String, CoffeeDTO> coffeeDTORedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {

        //TEST 01 : String 메시지 전송
        stringValueRedisTemplate.convertAndSend("ch01",     "Coffee, latte");

        //TEST 02 : DTO 메시지 전송
        CoffeeDTO coffeeDTO = new CoffeeDTO();
        coffeeDTO.setName("latte");
        coffeeDTO.setPrice(1100);
        coffeeDTORedisTemplate.convertAndSend("ch02", coffeeDTO);

    }
}