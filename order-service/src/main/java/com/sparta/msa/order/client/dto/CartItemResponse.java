package com.sparta.msa.order.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemResponse {
    Long id;
    Long productId;
    String productName;
    Long price;
    Integer quantity;
    Long totalPrice;
}
