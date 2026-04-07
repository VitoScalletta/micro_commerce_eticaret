package com.microcommerce.productservice.controller;

import com.microcommerce.productservice.dto.request.CreateProductRequestDto;
import com.microcommerce.productservice.dto.response.ProductResponseDto;
import com.microcommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody CreateProductRequestDto createProductRequestDto)
    {
        return ResponseEntity.ok(productService.createProduct(createProductRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody CreateProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.updateProduct(id,requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
