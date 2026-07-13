package com.sparta.msa.cart.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartItemRequest {

    @NotNull
    Long productId;

    @NotNull
    @Min(1)
    Integer quantity;
}
