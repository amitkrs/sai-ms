package com.techie.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(String orderNumber, String skuCode,
                           BigDecimal price, Integer quantity) {

//    public record UserDetails(String email, String firstName, String lastName) {}
}


