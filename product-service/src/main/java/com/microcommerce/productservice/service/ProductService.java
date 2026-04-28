package com.microcommerce.productservice.service;

import com.microcommerce.productservice.dto.event.OrderCreatedEvent;
import com.microcommerce.productservice.dto.event.StockReservedEvent;
import com.microcommerce.productservice.dto.request.CreateProductRequestDto;
import com.microcommerce.productservice.dto.response.ProductResponseDto;
import com.microcommerce.productservice.entity.Product;
import com.microcommerce.productservice.exception.ProductNotFoundException;
import com.microcommerce.productservice.messaging.publisher.ProductEventPublisher;
import com.microcommerce.productservice.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductEventPublisher productEventPublisher;

    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {
        Product product = modelMapper.map(requestDto, Product.class);
        Product savedproduct = productRepository.save(product);
        return modelMapper.map(savedproduct, ProductResponseDto.class);
    }

    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Bu ID'ye ait product bulunamadı  :" + id));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product ,ProductResponseDto.class))
                .toList();
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Silmek istediğiniz id Bulunamadı : " + id);
        }
        productRepository.deleteById(id);
    }

    public ProductResponseDto  updateProduct(Long id, CreateProductRequestDto requestDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Güncellemek istediğiniz id bulunamadı : " + id));

        existingProduct.setName(requestDto.getName());
        existingProduct.setPrice(requestDto.getPrice());
        existingProduct.setStockQuantity(requestDto.getStockQuantity());

        return modelMapper.map(productRepository.save(existingProduct), ProductResponseDto.class);
    }

    @Transactional
    public void reduceStock(OrderCreatedEvent event) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Ürün Bulunamadı"));

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Stok Yetersiz!");
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);
        System.out.println("Stok Başarıyla güncellendi! Ürün Id : "+productId+"Yeni Stok : "+ product.getStockQuantity() );
        StockReservedEvent stockReservedEvent = new StockReservedEvent(

        );
    }
}
