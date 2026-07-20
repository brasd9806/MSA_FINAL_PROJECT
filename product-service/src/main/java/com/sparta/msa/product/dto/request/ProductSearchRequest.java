package com.sparta.msa.product.dto.request;

import com.sparta.msa.product.enums.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchRequest {
    private String keyword;
    private Long categoryId;
    private Long minPrice;
    private Long maxPrice;
    private ProductStatus status;
}
