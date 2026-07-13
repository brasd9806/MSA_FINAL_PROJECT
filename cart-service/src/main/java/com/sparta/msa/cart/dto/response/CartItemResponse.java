package com.sparta.msa.cart.dto.response;

import com.sparta.msa.cart.entity.CartItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {

    Long id;
    Long productId;
    String productName;
    Long price;
    Integer quantity;
    Long totalPrice;

    public static CartItemResponse from(CartItem cartItem) {
        return CartItemResponse.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProductId())
                .productName(cartItem.getProductName())
                .price(cartItem.getProductPrice())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getProductPrice() * cartItem.getQuantity())
                .build();
    }
}
