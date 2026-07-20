package com.sparta.msa.ai.controller;

import com.sparta.msa.ai.dto.ProductRecommendRequest;
import com.sparta.msa.ai.dto.ReviewAnalyzeRequest;
import com.sparta.msa.ai.service.AiService;
import com.sparta.msa.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    @PostMapping("/reviews/analyze")
    public ResponseEntity<ApiResponse<String>> analyzeReview(@Valid @RequestBody ReviewAnalyzeRequest request) {
        return ApiResponse.success(aiService.analyzeReview(request));
    }

    @PostMapping("/products/recommend")
    public ResponseEntity<ApiResponse<String>> recommendProduct(@Valid @RequestBody ProductRecommendRequest request) {
        return ApiResponse.success(aiService.recommendProduct(request));
    }
}
