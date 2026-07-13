package com.sparta.msa.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRequest {

    @NotBlank
    String name;

    @NotNull
    @Min(0)
    Long price;

    @NotNull
    @Min(0)
    Integer stock;

    String description;

    @NotNull
    Long categoryId;
}
