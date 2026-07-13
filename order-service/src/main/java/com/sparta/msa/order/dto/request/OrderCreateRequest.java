package com.sparta.msa.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {

    @NotNull
    Long addressId;

    List<Long> cartItemIds;
}
