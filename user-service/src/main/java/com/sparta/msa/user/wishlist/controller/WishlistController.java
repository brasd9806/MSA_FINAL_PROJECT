package com.sparta.msa.user.wishlist.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.user.wishlist.dto.response.WishlistResponse;
import com.sparta.msa.user.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WishlistResponse>>> list(@PathVariable Long userId) {
        return ApiResponse.success(wishlistService.list(userId));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> add(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.add(userId, productId);
        return ApiResponse.success();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> remove(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.remove(userId, productId);
        return ApiResponse.success();
    }
}
