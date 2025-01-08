package com.arom.spring.msvc.items.clients;

import com.arom.spring.msvc.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(url = "localhost:8001/api/products", name = "msvc-products")
public interface ProductFeignClient {

    @GetMapping
    List<Product> getProducts();

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id);

}
