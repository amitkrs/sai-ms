package com.techie.microservices.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String name, String code,
                              Integer quantity, BigDecimal price) {
}
