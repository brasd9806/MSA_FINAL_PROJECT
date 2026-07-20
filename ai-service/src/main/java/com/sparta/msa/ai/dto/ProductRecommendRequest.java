package com.sparta.msa.ai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProductRecommendRequest {
    @NotBlank
    private String category;
    private String keyword;
    private Long budget;
    private Integer count = 3;
}
