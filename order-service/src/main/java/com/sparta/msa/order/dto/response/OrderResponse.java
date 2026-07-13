package com.sparta.msa.order.dto.response;

import com.sparta.msa.order.entity.Order;
import com.sparta.msa.order.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {

    Long id;
    Long userId;
    Long addressId;
    OrderStatus status;
    Long totalPrice;

    public static OrderResponse from(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .addressId(order.getAddressId())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
