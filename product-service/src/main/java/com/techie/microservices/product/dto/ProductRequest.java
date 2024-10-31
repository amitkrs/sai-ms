package com.techie.microservices.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        @NotEmpty(message = "Product name can't be empty")
        String name,

        @NotEmpty(message = "Product code can't be empty")
        String code,

        @NotNull(message = "Product quantity cannot be null")
        @Min(value = 1, message = "Product quantity must be greater than equal to 1")
        Integer quantity,

        @NotNull(message = "Product price cannot be null")
        @DecimalMin(value = "0.001", message = "Product price should be greater than 0.001")
        BigDecimal price) {
}
