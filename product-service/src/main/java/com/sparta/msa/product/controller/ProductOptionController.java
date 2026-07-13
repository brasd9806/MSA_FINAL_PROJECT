package com.sparta.msa.product.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.product.dto.request.ProductOptionRequest;
import com.sparta.msa.product.dto.response.ProductOptionResponse;
import com.sparta.msa.product.service.ProductOptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{productId}/options")
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductOptionResponse>>> list(@PathVariable Long productId) {
        return ApiResponse.success(productOptionService.list(productId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@PathVariable Long productId, @Valid @RequestBody ProductOptionRequest request) {
        productOptionService.save(productId, request);
        return ApiResponse.success();
    }

    @PutMapping("/{optionId}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long productId, @PathVariable Long optionId, @Valid @RequestBody ProductOptionRequest request) {
        productOptionService.update(optionId, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long productId, @PathVariable Long optionId) {
        productOptionService.delete(optionId);
        return ApiResponse.success();
    }
}
