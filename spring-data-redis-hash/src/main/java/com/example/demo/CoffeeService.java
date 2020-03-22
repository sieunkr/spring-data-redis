package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Coffee findByName(String name) {
        return coffeeRepository.findByName(name);
    }


}
