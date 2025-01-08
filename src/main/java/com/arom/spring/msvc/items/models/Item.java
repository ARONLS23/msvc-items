package com.arom.spring.msvc.items.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Product product;

    private int quantity;

    public Double getTotal(){
        return product.getPrice() * quantity;
    }

}
