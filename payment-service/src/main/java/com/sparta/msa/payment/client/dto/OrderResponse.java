package com.sparta.msa.payment.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderResponse {
    Long id;
    Long userId;
    Long addressId;
    String status;
    Long totalPrice;
}
