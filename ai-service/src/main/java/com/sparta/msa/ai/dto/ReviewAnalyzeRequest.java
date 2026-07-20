package com.sparta.msa.ai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReviewAnalyzeRequest {
    @NotBlank
    private String reviewText;
}
