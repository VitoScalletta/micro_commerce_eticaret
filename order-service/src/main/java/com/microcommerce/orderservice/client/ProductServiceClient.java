package com.microcommerce.orderservice.client;

import com.microcommerce.orderservice.dto.response.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping("/api/products/{id}")
    ProductResponseDto getProductById(@PathVariable Long id);

}
