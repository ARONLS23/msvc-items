package com.arom.spring.msvc.items.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Product {

    private Long id;

    private String name;

    private Double price;

    private LocalDate createAt;

}
