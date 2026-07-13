package com.sparta.msa.product.dto.response;

import com.sparta.msa.product.entity.Product;
import com.sparta.msa.product.enums.ProductStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;
    String name;
    Long price;
    Integer stock;
    String description;
    String category;
    ProductStatus status;
    Integer discountRate;
    Long discountPrice;
    LocalDateTime createdAt;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .category(product.getCategory().getName())
                .status(product.getStatus())
                .discountRate(product.getDiscountRate())
                .discountPrice(product.getDiscountPrice())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
