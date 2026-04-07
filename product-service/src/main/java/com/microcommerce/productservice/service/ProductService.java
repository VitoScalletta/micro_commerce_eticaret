package com.microcommerce.productservice.service;

import com.microcommerce.productservice.dto.response.ProductResponseDto;
import com.microcommerce.productservice.entity.Product;
import com.microcommerce.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

}
