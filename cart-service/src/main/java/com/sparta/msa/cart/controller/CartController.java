package com.sparta.msa.cart.controller;

import com.sparta.msa.cart.dto.request.CartItemRequest;
import com.sparta.msa.cart.dto.response.CartItemResponse;
import com.sparta.msa.cart.service.CartService;
import com.sparta.msa.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> list(@AuthenticationPrincipal Long userId) {
        return ApiResponse.success(cartService.list(userId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addItem(@AuthenticationPrincipal Long userId, @Valid @RequestBody CartItemRequest request) {
        cartService.addItem(userId, request);
        return ApiResponse.success();
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> updateItem(@AuthenticationPrincipal Long userId, @PathVariable Long cartItemId, @Valid @RequestBody CartItemRequest request) {
        cartService.updateItem(userId, cartItemId, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@AuthenticationPrincipal Long userId, @PathVariable Long cartItemId) {
        cartService.deleteItem(userId, cartItemId);
        return ApiResponse.success();
    }

    // Internal endpoint for order-service
    @GetMapping("/internal")
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> getByIds(@RequestParam List<Long> ids) {
        return ApiResponse.success(cartService.getByIds(ids));
    }

    @DeleteMapping("/internal")
    public ResponseEntity<ApiResponse<Void>> deleteByIds(@RequestParam List<Long> ids) {
        cartService.deleteByIds(ids);
        return ApiResponse.success();
    }
}
