package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CoffeeRepositoryImpl implements CoffeeRepository {

    private final RedisTemplate<String, Coffee> redisTemplate;
    private final HashOperations hashOperations;
    private final SetOperations<String, String> setOperations;
    private static String KEY_COFFEES_SET = "coffees";
    private static String KEY_COFFEES_HASH = "coffees:names:";

    public CoffeeRepositoryImpl(RedisTemplate<String, Coffee> redisTemplate, RedisTemplate<String, String> stringStringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
        setOperations = stringStringRedisTemplate.opsForSet();
    }

    @PostConstruct
    void init() {

        saveAsync(new Coffee("1", "americano", 900));
        saveAsync(new Coffee("2", "latte", 1100));
        saveAsync(new Coffee("3", "mocha", 1300));
    }

    private Map<String, String> makeCoffeeMap(String id, String name, String price) {

        Map<String, String> coffeeMap = new HashMap<>();
        coffeeMap.put("id", id);
        coffeeMap.put("name", name);
        coffeeMap.put("price", price);
        return coffeeMap;
    }

    @Override
    public Coffee findByName(String name) {

        return mapperToCoffee(name);
    }

    @Override
    public List<Coffee> findAll() {

        Set<String> coffeeSet = setOperations.members(KEY_COFFEES_SET);

        return Objects.requireNonNull(coffeeSet).stream()
                .map(this::mapperToCoffee)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Coffee coffee) {

        setOperations.add(KEY_COFFEES_SET, coffee.getName());
        hashOperations.putAll(KEY_COFFEES_HASH + coffee.getName(), makeCoffeeMap(coffee.getId(), coffee.getName(), String.valueOf(coffee.getPrice())));
        log.info("save: " + coffee.getName());
    }

    @Override
    public CompletableFuture<Void> saveAsync(Coffee coffee) {

        //TODO:Executor 커스텀풀 설정
        return CompletableFuture.runAsync(() -> save(coffee));
    }

    @Override
    public void delete() {

    }

    private Coffee mapperToCoffee(String name) {

        Assert.notNull(name, "Name must not be null");

        Map coffeeMap = hashOperations.entries(KEY_COFFEES_HASH + name);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(coffeeMap, Coffee.class);
    }
}
