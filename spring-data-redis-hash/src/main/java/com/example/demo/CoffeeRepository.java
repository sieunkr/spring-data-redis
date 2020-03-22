package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface CoffeeRepository {

    Coffee findByName(String name);
    List<Coffee> findAll();
    void save(Coffee coffee);
    CompletableFuture<Void> saveAsync(Coffee coffee);
    void delete();

}