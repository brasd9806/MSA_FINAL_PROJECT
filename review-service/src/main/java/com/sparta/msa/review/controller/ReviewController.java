package com.sparta.msa.review.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.review.dto.request.ReviewRequest;
import com.sparta.msa.review.dto.response.ReviewResponse;
import com.sparta.msa.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> create(
            @AuthenticationPrincipal Long userId,
            @RequestParam Long productId,
            @RequestParam Long orderId,
            @Valid @RequestBody ReviewRequest request) {
        return ApiResponse.created(reviewService.create(userId, productId, orderId, request));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> listByProduct(@PathVariable Long productId) {
        return ApiResponse.success(reviewService.listByProduct(productId));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> listByUser(@AuthenticationPrincipal Long userId) {
        return ApiResponse.success(reviewService.listByUser(userId));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewResponse>> update(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewRequest request) {
        return ApiResponse.success(reviewService.update(userId, reviewId, request));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long reviewId) {
        reviewService.delete(userId, reviewId);
        return ApiResponse.success();
    }
}
