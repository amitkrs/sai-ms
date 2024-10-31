package com.techie.microservices.product.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(value = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private String id;

    private String name;

    @Indexed(name = "index_code", unique = true)
    private String code;

    private BigDecimal price;

    @Field(write = Field.Write.ALWAYS)
    private Integer quantity;
}
