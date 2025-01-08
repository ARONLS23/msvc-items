package com.arom.spring.msvc.items.services;

import com.arom.spring.msvc.items.clients.ProductFeignClient;
import com.arom.spring.msvc.items.models.Item;
import com.arom.spring.msvc.items.models.Product;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceFeign implements ItemService{

    private final ProductFeignClient client;
    @Override
    public List<Item> findAll() {
        return client.getProducts()
                .stream()
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {
        try {
            Product product = client.getProductById(id);
            return Optional.of(new Item(product, new Random().nextInt(10) + 1));
        }catch (FeignException e) {
            return Optional.empty();
        }
    }
}
