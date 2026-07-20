package com.sparta.msa.coupon.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.coupon.dto.request.CouponCreateRequest;
import com.sparta.msa.coupon.dto.response.CouponResponse;
import com.sparta.msa.coupon.dto.response.UserCouponResponse;
import com.sparta.msa.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<ApiResponse<CouponResponse>> create(@RequestBody CouponCreateRequest request) {
        return ApiResponse.created(couponService.create(request));
    }

    @PostMapping("/issue/{code}")
    public ResponseEntity<ApiResponse<UserCouponResponse>> issue(
            @AuthenticationPrincipal Long userId,
            @PathVariable String code) {
        return ApiResponse.created(couponService.issue(userId, code));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<UserCouponResponse>>> listMyCoupons(@AuthenticationPrincipal Long userId) {
        return ApiResponse.success(couponService.listMyCoupons(userId));
    }

    @PostMapping("/use/{userCouponId}")
    public ResponseEntity<ApiResponse<UserCouponResponse>> use(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long userCouponId) {
        return ApiResponse.success(couponService.use(userId, userCouponId));
    }
}
