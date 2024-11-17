package com.techie.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private String id;

    private String name;

    //    @Indexed(name = "index_code", unique = true)
    private String code;

    private BigDecimal price;

    private Integer quantity;
}
