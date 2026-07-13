package com.sparta.msa.product.dto.response;

import com.sparta.msa.product.entity.ProductOption;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOptionResponse {

    Long id;
    Long productId;
    String name;
    BigDecimal additionalPrice;
    Integer stock;

    public static ProductOptionResponse from(ProductOption option) {
        return ProductOptionResponse.builder()
                .id(option.getId())
                .productId(option.getProduct().getId())
                .name(option.getName())
                .additionalPrice(option.getAdditionalPrice())
                .stock(option.getStock())
                .build();
    }
}
