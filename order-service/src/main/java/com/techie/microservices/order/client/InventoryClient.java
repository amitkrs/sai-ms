package com.techie.microservices.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Slf4j
@FeignClient(value = "inventory-service", url = "http://localhost:8082")
public interface InventoryClient {

    //    @GetExchange("/api/inventory")
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @Retry(name = "inventory")
    @GetMapping(value = "/api/inventory")
    ResponseEntity<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

//    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
//        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
//        return false;
//    }
}
