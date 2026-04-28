package com.microcommerce.productservice.messaging.consumer;

import com.microcommerce.productservice.dto.event.StockReservedEvent;
import com.microcommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockReservedEventConsumer {
    private final ProductService productService;

    public void StockReservedEvent(StockReservedEvent event){
        System.out.println("Stock Reserved Event ürün id ");
    }
}
