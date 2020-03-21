package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
class CoffeeDTO implements Serializable {

    private String name;
    private int price;
}