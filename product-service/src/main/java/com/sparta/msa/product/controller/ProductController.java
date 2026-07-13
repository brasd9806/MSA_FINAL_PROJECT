package com.sparta.msa.product.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.product.dto.request.ProductRequest;
import com.sparta.msa.product.dto.response.ProductResponse;
import com.sparta.msa.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> list() {
        return ApiResponse.success(productService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(@PathVariable Long id) {
        return ApiResponse.success(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody ProductRequest request) {
        productService.save(request);
        return ApiResponse.success();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        productService.update(id, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ApiResponse.success();
    }
}
