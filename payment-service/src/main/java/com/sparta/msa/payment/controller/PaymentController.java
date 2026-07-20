package com.sparta.msa.payment.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.payment.dto.request.CancelRequest;
import com.sparta.msa.payment.dto.response.PaymentLogResponse;
import com.sparta.msa.payment.dto.response.PaymentResponse;
import com.sparta.msa.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    // 결제
    @PostMapping("/orders/{orderId}")
    public ResponseEntity<ApiResponse<PaymentResponse>> pay(@PathVariable Long orderId) {
        return ApiResponse.success(paymentService.pay(orderId));
    }

    // 결제 조회 (주문 기준)
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> list(@PathVariable Long orderId) {
        return ApiResponse.success(paymentService.list(orderId));
    }

    // 결제 로그 조회
    @GetMapping("/{paymentId}/logs")
    public ResponseEntity<ApiResponse<List<PaymentLogResponse>>> logs(@PathVariable Long paymentId) {
        return ApiResponse.success(paymentService.logs(paymentId));
    }

    // 취소 (전액 or 부분)
    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<ApiResponse<PaymentResponse>> cancel(
            @PathVariable Long paymentId,
            @Valid @RequestBody CancelRequest request) {
        return ApiResponse.success(paymentService.cancel(paymentId, request));
    }
}
