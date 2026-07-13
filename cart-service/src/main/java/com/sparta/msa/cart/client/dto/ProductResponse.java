package com.sparta.msa.cart.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponse {
    Long id;
    String name;
    Long price;
    Integer stock;
    String description;
    String category;
    String status;
}
