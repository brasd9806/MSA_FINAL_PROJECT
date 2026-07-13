package com.sparta.msa.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductOptionRequest {

    @NotBlank
    String name;

    @NotNull
    @Min(0)
    BigDecimal additionalPrice;

    @NotNull
    @Min(0)
    Integer stock;
}
