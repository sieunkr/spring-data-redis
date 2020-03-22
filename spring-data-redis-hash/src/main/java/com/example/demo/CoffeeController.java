package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping
    public List<Coffee> coffees(){

        return coffeeService.findAll();
    }

    @GetMapping("/{name}")
    public Coffee coffee(@PathVariable(name = "name") String name){

        return coffeeService.findByName(name);
    }

}