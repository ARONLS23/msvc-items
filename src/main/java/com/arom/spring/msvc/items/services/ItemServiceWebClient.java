package com.arom.spring.msvc.items.services;

import com.arom.spring.msvc.items.models.Item;
import com.arom.spring.msvc.items.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServiceWebClient implements ItemService {

    private final WebClient.Builder client;

    @Override
    public List<Item> findAll() {
        return this.client.build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        try {
            return Optional.ofNullable(client.build()
                    .get()
                    .uri("/{id}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .map(product -> new Item(product, new Random().nextInt(10) + 1))
                    .block());
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }

    }
}
