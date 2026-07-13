package com.sparta.msa.payment.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.payment.dto.response.PaymentResponse;
import com.sparta.msa.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<ApiResponse<PaymentResponse>> pay(@PathVariable Long orderId) {
        return ApiResponse.success(paymentService.pay(orderId));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> list(@PathVariable Long orderId) {
        return ApiResponse.success(paymentService.list(orderId));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long paymentId) {
        paymentService.cancel(paymentId);
        return ApiResponse.success();
    }
}
