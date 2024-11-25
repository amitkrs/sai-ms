package com.techie.microservices.order.service;

import com.techie.microservices.order.client.InventoryClient;
import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.exception.CustomRuntimeException;
import com.techie.microservices.order.model.Order;
import com.techie.microservices.order.repository.OrderRepository;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;


//    private final InventoryClient inventoryClient;
//    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

//    public void placeOrder(OrderRequest orderRequest) {
//
//        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
//        if (isProductInStock) {
//            Order order = new Order();
//            order.setOrderNumber(UUID.randomUUID().toString());
//            order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
//            order.setSkuCode(orderRequest.skuCode());
//            order.setQuantity(orderRequest.quantity());
//            orderRepository.save(order);
//
//            // Send the message to Kafka Topic
//            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
//            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
//            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
//            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
//            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
//            log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
//            kafkaTemplate.send("order-placed", orderPlacedEvent);
//            log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
//        } else {
//            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
//        }
//    }

    public void placeOrder(OrderRequest orderRequest) {
        try {
//            boolean isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
            ResponseEntity<Boolean> invResponse = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
            boolean isInStock = invResponse.getBody();
            log.info("Inventory Store Check for order {} response is {} ", orderRequest, isInStock);
            if (!isInStock) {
                throw new CustomRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, "Products not in stock");
            }
            Order order = Order.builder().orderNumber(UUID.randomUUID().toString())
                    .skuCode(orderRequest.skuCode()).quantity(orderRequest.quantity())
                    .price(orderRequest.price())
                    .build();
            orderRepository.save(order);
        } catch (RetryableException ex) {
            log.error("Feign Retry exception {}", ex.getMessage());
            throw new CustomRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        } catch (FeignException ex) {
            ex.printStackTrace();
            throw new CustomRuntimeException(HttpStatusCode.valueOf(ex.status()), "Unable to connect inventory ", ex);
        }
    }

}
