package com.sparta.msa.point.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.point.dto.request.PointEarnRequest;
import com.sparta.msa.point.dto.request.PointUseRequest;
import com.sparta.msa.point.dto.response.PointBalanceResponse;
import com.sparta.msa.point.dto.response.PointHistoryResponse;
import com.sparta.msa.point.service.PointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;

    @PostMapping("/earn")
    public ResponseEntity<ApiResponse<PointHistoryResponse>> earn(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestBody PointEarnRequest request) {
        return ApiResponse.created(pointService.earn(userId, request));
    }

    @PostMapping("/use")
    public ResponseEntity<ApiResponse<PointHistoryResponse>> use(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestBody PointUseRequest request) {
        return ApiResponse.success(pointService.use(userId, request));
    }

    @GetMapping("/balance")
    public ResponseEntity<ApiResponse<PointBalanceResponse>> getBalance(@AuthenticationPrincipal Long userId) {
        return ApiResponse.success(pointService.getBalance(userId));
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<PointHistoryResponse>>> getHistory(@AuthenticationPrincipal Long userId) {
        return ApiResponse.success(pointService.getHistory(userId));
    }
}
